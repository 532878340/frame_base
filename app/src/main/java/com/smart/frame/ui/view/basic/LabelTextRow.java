package com.smart.frame.ui.view.basic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.frame.R;
import com.smart.frame.utils.DimensUtils;
import com.smart.frame.utils.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 通用Label和文本view
 *
 * @author Gjm
 * @date 2018/3/1
 */
public class LabelTextRow extends LinearLayout {
    private static final int INVALID = -1;
    @BindView(R.id.label)
    TextView mLabel;
    @BindView(R.id.desc)
    TextView mDesc;

    /**
     * 是否含有顶部divider
     */
    private boolean mWithTopDivider;
    /**
     * 是否含有底部divider
     */
    private boolean mWithBottomDivider;

    /**
     * 底部divider 边距
     */
    private int mDividerIndent;

    private Paint mPaint;

    public LabelTextRow(Context context) {
        this(context, null);
    }

    public LabelTextRow(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelTextRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(getContext(), R.layout.base_label_row_text, this);

        setMinimumHeight(DimensUtils.ptToPx(100));
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        final int defaultPadding = DimensUtils.ptToPx(30);
        setPadding(defaultPadding,0,defaultPadding,0);

        if(getBackground() == null){
            setBackgroundResource(R.drawable.sel_white_gray_rect);
        }

        ButterKnife.bind(this);

        initPaint();
        bindAttrs(attrs);
    }

    /**
     * 初始化 paint
     */
    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.divider));
    }

    /**
     * 绑定属性
     * @param attrs
     */
    private void bindAttrs(AttributeSet attrs){
        if(attrs != null){
            TypedArray styled = getContext().obtainStyledAttributes(attrs,R.styleable.LabelTextRow);

            //label
            CharSequence label = styled.getText(R.styleable.LabelTextRow_labelText);
            if(!TextUtils.isEmpty(label)){
                mLabel.setText(label);

                int labelColor = styled.getColor(R.styleable.LabelTextRow_labelTextColor,INVALID);
                if(labelColor != INVALID){
                    mLabel.setTextColor(labelColor);
                }

                int labelSize = styled.getDimensionPixelSize(R.styleable.LabelTextRow_labelTextSize,INVALID);
                if(labelSize != INVALID){
                    mLabel.setTextSize(labelSize);
                }
            }

            int exactlyWidth = styled.getDimensionPixelSize(R.styleable.LabelTextRow_labelExactlyWidth,INVALID);
            if(exactlyWidth != INVALID){
                mLabel.getLayoutParams().width = exactlyWidth;
            }

            //desc
            CharSequence desc = styled.getText(R.styleable.LabelTextRow_labelValue);
            if(!TextUtils.isEmpty(desc)){
                mDesc.setText(desc);

                int descColor = styled.getColor(R.styleable.LabelTextRow_labelValueColor,INVALID);
                if(descColor != INVALID){
                    mDesc.setTextColor(descColor);
                }

                int descSize = styled.getDimensionPixelSize(R.styleable.LabelTextRow_labelValueSize,INVALID);
                if(descSize != INVALID){
                    mDesc.setTextSize(descSize);
                }

                boolean alignRight = styled.getBoolean(R.styleable.LabelTextRow_labelValueRight,true);
                if(!alignRight){
                    mDesc.setGravity(Gravity.LEFT);
                }
            }

            boolean drawArrow = styled.getBoolean(R.styleable.LabelTextRow_labelArrows,false);
            if(drawArrow){
                mDesc.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_right,0);
            }

            int labelIcon = styled.getResourceId(R.styleable.LabelTextRow_labelIcon,INVALID);
            if(labelIcon != INVALID){
                mLabel.setCompoundDrawablesWithIntrinsicBounds(labelIcon,0,0,0);

                int scaleLeft = styled.getResourceId(R.styleable.LabelTextRow_labelScaleIcon, INVALID);
                if(scaleLeft != INVALID && !isInEditMode()){
                    Drawable left = getResources().getDrawable(scaleLeft);
                    final int size = DimensUtils.ptToPx(40);
                    left.setBounds(0, 0, size, size);
                    mLabel.setCompoundDrawables(left, null, null, null);
                }
            }

            mWithTopDivider = styled.getBoolean(R.styleable.LabelTextRow_labelTopDivider,false);
            mWithBottomDivider = styled.getBoolean(R.styleable.LabelTextRow_labelBottomDivider,true);

            if(mWithBottomDivider){
                mDividerIndent = styled.getDimensionPixelSize(R.styleable.LabelTextRow_labelDividerIndent,0);
            }

            styled.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mWithBottomDivider) {
            int y = getHeight() - 1;
            canvas.drawLine(mDividerIndent, y, getRight(), y, mPaint);
        }
        if (mWithTopDivider) {
            canvas.drawLine(0, 0, getRight(), 0, mPaint);
        }
    }

    /**
     * label view
     */
    public TextView getLabel(){
        return mLabel;
    }

    /**
     * desc view
     */
    public TextView getDesc(){
        return mDesc;
    }

    /**
     * label text
     */
    public String getLabelText(){
        return ViewUtil.getText(mLabel);
    }

    /**
     * desc text
     */
    public String getDescText(){
        return ViewUtil.getText(mDesc);
    }

    /**
     * set label
     */
    public void setLabelText(CharSequence charSequence){
        mLabel.setText(charSequence);
    }

    /**
     * set desc
     */
    public void setDescText(CharSequence charSequence){
        mDesc.setText(charSequence);
    }
}
