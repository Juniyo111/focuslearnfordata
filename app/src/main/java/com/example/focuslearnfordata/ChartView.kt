package com.example.focuslearnfordata

import android.graphics.Color
import android.view.LayoutInflater
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter

@Composable
fun LineChartView() {
    val context = LocalContext.current
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.line_chart_layout, null, false)
            val lineChart = view.findViewById<LineChart>(R.id.lineChart)

            val entries = listOf(
                Entry(0f, 0f),
                Entry(5f, 20f),
                Entry(10f, 40f),
                Entry(15f, 60f),
                Entry(20f, 80f),
                Entry(25f, 100f)
            )

            val dataSet = LineDataSet(entries, "Label").apply {
                color = Color.BLACK
                lineWidth = 2f
                setDrawValues(false)
                setDrawCircles(true)
                setCircleColor(Color.BLACK)
                setDrawCircleHole(true)
                circleHoleColor = Color.GRAY
            }

            val lineData = LineData(dataSet)
            lineChart.data = lineData

            lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            lineChart.xAxis.valueFormatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return when (value.toInt()) {
                        0 -> "1 Jun"
                        5 -> "5 Jun"
                        10 -> "10 Jun"
                        15 -> "15 Jun"
                        20 -> "20 Jun"
                        25 -> "25 Jun"
                        else -> ""
                    }
                }
            }

            lineChart.axisLeft.valueFormatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return "${value.toInt()}%"
                }
            }

            lineChart.axisRight.isEnabled = false

            lineChart.invalidate()
            view
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    )
}
