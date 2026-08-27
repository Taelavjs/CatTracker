package com.example.cattracker.graphing.line_graph

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.cattracker.graphing.InsulinData

private const val HOURS_IN_DAY = 24f
private const val X_STEPS = 6
private const val Y_MAX = 30f
private const val Y_STEPS = 6

private fun timeToX(
    hour: Int,
    minute: Int
): Float {
    val hourOfDay = hour + minute / 60f

    return hourOfDay / (HOURS_IN_DAY / X_STEPS)
}

@Composable
fun PlotLineGraph(
    pointsData: List<Point>
) {
    if (pointsData.isEmpty()) return

    val xAxisData = AxisData.Builder()
        .steps(X_STEPS)
        .axisStepSize(55.dp)
        .labelData { i ->
            "%02d:00".format(i * 4)
        }
        .labelAndAxisLinePadding(10.dp)
        .shouldDrawAxisLineTillEnd(true)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(Y_STEPS)
        .labelAndAxisLinePadding(15.dp)
        .shouldDrawAxisLineTillEnd(true)
        .labelData { i ->
            "%.1f".format(i * (Y_MAX / Y_STEPS))
        }
        .build()

    val boundsLine = Line(
        dataPoints = listOf(
            Point(0f, 0f),
            Point(X_STEPS.toFloat(), Y_MAX)
        ),
        lineStyle = LineStyle(
            color = Color.Transparent
        ),
        intersectionPoint = IntersectionPoint(
            color = Color.Transparent
        ),
        selectionHighlightPoint = SelectionHighlightPoint(
            color = Color.Transparent
        ),
        shadowUnderLine = ShadowUnderLine(
            alpha = 0f
        ),
        selectionHighlightPopUp = SelectionHighlightPopUp()
    )

    val insulinLine = Line(
        dataPoints = pointsData,
        lineStyle = LineStyle(),
        intersectionPoint = IntersectionPoint(),
        selectionHighlightPoint = SelectionHighlightPoint(),
        shadowUnderLine = ShadowUnderLine(
            alpha = 0f
        ),
        selectionHighlightPopUp = SelectionHighlightPopUp()
    )

    val highReadingsLine = FilterPointsColourValue(
        pointsData = pointsData,
        comparisonFunction = { yValue : Float ->
            yValue > 13f
        },
        color = Color.Blue
    )

    val lowReadingsLine = FilterPointsColourValue(
        pointsData = pointsData,
        comparisonFunction = { yValue : Float ->
            yValue <= 3f
        },
        color = Color.Red
    )

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                boundsLine,
                insulinLine,
                highReadingsLine,
                lowReadingsLine
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.Transparent
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        lineChartData = lineChartData
    )
}

@Composable
fun TransformInsulinReadingsIntoPoints(
    insulinReadings: List<InsulinData>
) {
    val readingGraphPoints = insulinReadings.map { reading ->
        Point(
            x = timeToX(
                hour = reading.time.hour,
                minute = reading.time.minute
            ),
            y = reading.insulinReading
        )
    }


    PlotLineGraph(
        pointsData = readingGraphPoints
    )
}

fun FilterPointsColourValue(
    pointsData: List<Point>,
    comparisonFunction: (Float) -> Boolean,
    color: Color
): Line {
    return Line(
        dataPoints = pointsData.filter { comparisonFunction(it.y) },
        lineStyle = LineStyle(
            color = color
        ),
        intersectionPoint = IntersectionPoint(
            color = color
        ),
        selectionHighlightPoint = SelectionHighlightPoint(
            color = color
        ),
        shadowUnderLine = ShadowUnderLine(
            alpha = 0f
        ),
        selectionHighlightPopUp = SelectionHighlightPopUp()
    )

}
