package com.strikalov.indexfeargreed.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.strikalov.indexfeargreed.R
import android.graphics.LinearGradient
import android.os.Build
import android.support.annotation.RequiresApi

class CustomView: View {

    companion object {
        private const val DEFAULT_FIRST_ARC_COLOR = Color.RED
        private const val DEFAULT_SECOND_ARC_COLOR = Color.YELLOW
        private const val DEFAULT_THIRD_ARC_COLOR = Color.GREEN
        private const val DEFAULT_ARROW_COLOR = Color.BLACK
        private const val DEFAULT_ARROW_TEXT_COLOR = Color.WHITE
        private const val DEFAULT_ARC_WIDTH = 50f
        private const val DEFAULT_ARROW_RADIUS = 50f
        private const val DEFAULT_ARROW_LENGTH = 200f
        private const val DEFAULT_ARROW_WIDTH = 15f
        private const val DEFAULT_INDEX_FEAR_GREED_VALUE = 0
        private const val DEFAULT_ARC_ANGEL = 180f
        private const val DEFAULT_FONT_SIZE = 55f
    }

    private val arcPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fontPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowPath: Path = Path()
    private val arrowMatrix: Matrix = Matrix()
    private val oval: RectF = RectF()

    private var firstArcColor: Int = DEFAULT_FIRST_ARC_COLOR
    private var secondArcColor: Int = DEFAULT_SECOND_ARC_COLOR
    private var thirdArcColor: Int = DEFAULT_THIRD_ARC_COLOR
    private var arrowColor: Int = DEFAULT_ARROW_COLOR
    private var arrowTextColor: Int = DEFAULT_ARROW_TEXT_COLOR
    private var arcWidth: Float = DEFAULT_ARC_WIDTH
    private var arrowRadius: Float = DEFAULT_ARROW_RADIUS
    private var arrowLength: Float = DEFAULT_ARROW_LENGTH
    private var arrowWidth: Float = DEFAULT_ARROW_WIDTH
    private var fontSize: Float = DEFAULT_FONT_SIZE

    var indexFearGreedValue: Int = DEFAULT_INDEX_FEAR_GREED_VALUE
        set(value){
            field = value
            invalidate()
        }

    private var radius: Float = 0f
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        setupAttrs(context, attrs,0,0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        setupAttrs(context, attrs,defStyleAttr,0)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int):
            super(context, attrs, defStyleAttr, defStyleRes){
        setupAttrs(context, attrs,defStyleAttr,defStyleRes)
    }

    private fun setupAttrs(context:Context, attrs:AttributeSet, defStyleAttr: Int, defStyleRes: Int){

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, defStyleRes)

        try {

            firstArcColor = typedArray.getColor(R.styleable.CustomView_firstArcColor, DEFAULT_FIRST_ARC_COLOR)
            secondArcColor = typedArray.getColor(R.styleable.CustomView_secondArcColor, DEFAULT_SECOND_ARC_COLOR)
            thirdArcColor = typedArray.getColor(R.styleable.CustomView_thirdArcColor, DEFAULT_THIRD_ARC_COLOR)
            arcWidth = typedArray.getDimension(R.styleable.CustomView_arcWidth, DEFAULT_ARC_WIDTH)
            arrowColor = typedArray.getColor(R.styleable.CustomView_arrowColor, DEFAULT_ARROW_COLOR)
            arrowTextColor = typedArray.getColor(R.styleable.CustomView_arrowTextColor, DEFAULT_ARROW_TEXT_COLOR)
            arrowRadius = typedArray.getDimension(R.styleable.CustomView_arrowRadius, DEFAULT_ARROW_RADIUS)
            arrowLength = typedArray.getDimension(R.styleable.CustomView_arrowLength, DEFAULT_ARROW_LENGTH)
            indexFearGreedValue = typedArray.getInt(R.styleable.CustomView_indexFearGreedValue, DEFAULT_INDEX_FEAR_GREED_VALUE)
            arrowWidth = typedArray.getDimension(R.styleable.CustomView_arrowWidth, DEFAULT_ARROW_WIDTH)
            fontSize = typedArray.getDimension(R.styleable.CustomView_fontSize, DEFAULT_FONT_SIZE)

        } finally {
            typedArray.recycle()
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    override fun onDrawForeground(canvas: Canvas?) {
        super.onDrawForeground(canvas)
        val width = width
        val height = height

        radius = (width / 2.5f)
        centerX = width / 2f
        centerY = height - (arrowRadius + arrowWidth) * 1.2f

        drawArc(canvas)
        drawArrow(canvas)
    }

    private fun drawArc(canvas: Canvas?) {

        arcPaint.shader = LinearGradient(
            centerX - radius, 0f, centerX + radius, 0f,
            intArrayOf(firstArcColor, secondArcColor, thirdArcColor), floatArrayOf(0f, 0.5f, 1f), Shader.TileMode.CLAMP
        )

        arcPaint.strokeWidth = arcWidth
        arcPaint.style = Paint.Style.STROKE

        oval.set(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )

        canvas?.drawArc(oval, DEFAULT_ARC_ANGEL, DEFAULT_ARC_ANGEL, false, arcPaint)

    }

    private fun drawArrow(canvas: Canvas?) {

        arrowPaint.color = arrowColor
        arrowPaint.style = Paint.Style.FILL_AND_STROKE

        arrowPath.reset()

        arrowPath.addCircle(centerX, centerY, arrowRadius, Path.Direction.CW)

        arrowPaint.strokeWidth = arrowWidth

        arrowPath.moveTo(centerX, centerY)
        arrowPath.lineTo(centerX - arrowLength, centerY)

        if (indexFearGreedValue < 0) {
            indexFearGreedValue = 0
        } else if (indexFearGreedValue > 100) {
            indexFearGreedValue = 100
        }

        val rotateArrowAngle = DEFAULT_ARC_ANGEL * (indexFearGreedValue / 100f)

        arrowMatrix.reset()
        arrowMatrix.setRotate(rotateArrowAngle, centerX, centerY)

        arrowPath.transform(arrowMatrix)

        canvas?.drawPath(arrowPath, arrowPaint)

        fontPaint.textSize = fontSize
        fontPaint.style = Paint.Style.STROKE
        fontPaint.color = arrowTextColor
        fontPaint.textAlign = Paint.Align.CENTER
        val indexText = Integer.toString(indexFearGreedValue)
        val textHeight = -(fontPaint.fontMetrics.ascent + fontPaint.fontMetrics.descent)
        canvas?.drawText(indexText, centerX, centerY + textHeight / 2f, fontPaint)

    }


}