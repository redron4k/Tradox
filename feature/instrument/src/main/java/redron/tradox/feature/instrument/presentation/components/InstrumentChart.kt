package redron.tradox.feature.instrument.presentation.components

import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import redron.tradox.domain.model.instrument.InstrumentPoint
import kotlin.collections.forEachIndexed
import kotlin.math.max
import kotlin.math.min

@Composable
fun InstrumentChart(
    points: List<InstrumentPoint>,
    modifier: Modifier = Modifier,
    isProMode: Boolean = true,
    gridColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    lineColor: Color = Color(0xFF5655B9),
    avgLineColor: Color = Color(0xFFA8A3D7),
) {
    if (points.isEmpty()) return

    val maxPrice = points.maxOf { it.highPrice }
    val minPrice = points.minOf { it.lowPrice }
    val priceRange = maxPrice - minPrice

    Canvas(modifier = modifier) {
        val widthPerPoint = size.width / (points.size * 1.5f)
        val spacing = widthPerPoint / 2

        val horizontalLines = 4
        for (i in 0..horizontalLines) {
            val y = size.height * i / horizontalLines
            drawLine(
                color = gridColor,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1f
            )
        }

        val verticalLines = min(4, points.size - 1)
        val labelPaint = TextPaint().apply {
            color = android.graphics.Color.BLACK
            textAlign = android.graphics.Paint.Align.CENTER
            textSize = 30f
        }
        for (i in 0..verticalLines) {
            val index = i * (points.size - 1) / verticalLines
            val x = index * (widthPerPoint + spacing)
            drawLine(
                color = gridColor,
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = 1f
            )
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    points[index].date,
                    x,
                    size.height + 30f,
                    labelPaint
                )
            }
        }

        if (isProMode) {
            points.forEachIndexed { index, point ->
                val x = index * (widthPerPoint + spacing)
                fun priceToY(price: Double) = size.height - ((price - minPrice) / priceRange * size.height).toFloat()
                val openY = priceToY(point.openPrice)
                val closeY = priceToY(point.closePrice)
                val highY = priceToY(point.highPrice)
                val lowY = priceToY(point.lowPrice)
                val color = if (point.closePrice >= point.openPrice) Color(0xFF2E7D32) else Color(0xFFC62828)

                drawLine(
                    color = color,
                    start = Offset(x + widthPerPoint / 2, highY),
                    end = Offset(x + widthPerPoint / 2, lowY),
                    strokeWidth = 2f
                )

                drawRect(
                    color = color,
                    topLeft = Offset(x, min(openY, closeY)),
                    size = androidx.compose.ui.geometry.Size(widthPerPoint, max(2f, kotlin.math.abs(closeY - openY)))
                )
            }
        } else {
            val path = Path()
            points.forEachIndexed { index, point ->
                val x = index * (widthPerPoint + spacing)
                val y = size.height - ((point.closePrice - minPrice) / priceRange * size.height).toFloat()
                if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
            }
            drawPath(path, color = lineColor, style = Stroke(width = 3f))

            val avg = points.map { it.closePrice }.average()
            val yAvg = size.height - ((avg - minPrice) / priceRange * size.height).toFloat()
            drawLine(
                color = avgLineColor,
                start = Offset(0f, yAvg),
                end = Offset(size.width, yAvg),
                strokeWidth = 2f,
                pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        }
    }
}
