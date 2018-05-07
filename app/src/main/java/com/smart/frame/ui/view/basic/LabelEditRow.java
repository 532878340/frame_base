package com.smart.frame.ui.view.basic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;

import com.smart.frame.R;
import com.smart.frame.utils.DimensUtils;

/**
 * 通用Label和输入框 view
 *
 * @author Gjm
 * @date 2018/3/5
 */
public class LabelEditRow extends LabelTextRow {
    public LabelEditRow(Context context) {
        this(context, null);
    }

    public LabelEditRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.base_label_row_edit;
    }

    /**
     * 绑定属性
     *
     * @param attrs
     */
    protected void bindAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray styled = getContext().obtainStyledAttributes(attrs, R.styleable.LabelTextRow);

            //label
            CharSequence label = styled.getText(R.styleable.LabelTextRow_labelText);
            if (!TextUtils.isEmpty(label)) {
                mLabel.setText(label);

                int labelColor = styled.getColor(R.styleable.LabelTextRow_labelTextColor, INVALID);
                if (labelColor != INVALID) {
                    mLabel.setTextColor(labelColor);
                }

                int labelSize = styled.getDimensionPixelSize(R.styleable.LabelTextRow_labelTextSize, INVALID);
                if (labelSize != INVALID) {
                    mLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, labelSize);
                }
            }

            //desc
            CharSequence desc = styled.getText(R.styleable.LabelTextRow_labelValue);
            if (!TextUtils.isEmpty(desc)) {
                mDesc.setText(desc);

                int descColor = styled.getColor(R.styleable.LabelTextRow_labelValueColor, INVALID);
                if (descColor != INVALID) {
                    mDesc.setTextColor(descColor);
                }

                int descSize = styled.getDimensionPixelSize(R.styleable.LabelTextRow_labelValueSize, INVALID);
                if (descSize != INVALID) {
                    mDesc.setTextSize(TypedValue.COMPLEX_UNIT_PX, descSize);
                }
            }

            boolean drawArrow = styled.getBoolean(R.styleable.LabelTextRow_labelArrows, false);
            if (drawArrow) {
                mDesc.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_right, 0);
            }

            int labelIcon = styled.getResourceId(R.styleable.LabelTextRow_labelIcon, INVALID);
            if (labelIcon != INVALID) {
                mLabel.setCompoundDrawablesWithIntrinsicBounds(labelIcon, 0, 0, 0);

                int scaleLeft = styled.getResourceId(R.styleable.LabelTextRow_labelScaleIcon, INVALID);
                if (scaleLeft != INVALID && !isInEditMode()) {
                    Drawable left = getResources().getDrawable(scaleLeft);
                    final int size = DimensUtils.ptToPx(getContext(),40);
                    left.setBounds(0, 0, size, size);
                    mLabel.setCompoundDrawables(left, null, null, null);
                }
            }

            mWithTopDivider = styled.getBoolean(R.styleable.LabelTextRow_labelTopDivider, false);
            mWithBottomDivider = styled.getBoolean(R.styleable.LabelTextRow_labelBottomDivider, true);

            if (mWithBottomDivider) {
                mDividerIndent = styled.getDimensionPixelSize(R.styleable.LabelTextRow_labelDividerIndent, 0);
            }

            styled.recycle();

            //EditText相关属性
            TypedArray styledEdit = getContext().obtainStyledAttributes(attrs, R.styleable.LabelEditRow);

            int exactlyWidth = styledEdit.getDimensionPixelSize(R.styleable.LabelEditRow_labelExactlyWidth, INVALID);
            if (exactlyWidth != INVALID) {
                mLabel.setMinWidth(exactlyWidth);
            }

            boolean alignRight = styledEdit.getBoolean(R.styleable.LabelEditRow_labelValueRight, false);
            if (alignRight) {
                mDesc.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            }

            int inputType = styledEdit.getInt(R.styleable.LabelEditRow_android_inputType, EditorInfo.TYPE_CLASS_TEXT);
            mDesc.setInputType(inputType);

            mDesc.setEnabled(styledEdit.getBoolean(R.styleable.LabelEditRow_labelEditEnable, true));

            // 最大长度
            int maxLength = styledEdit.getInt(R.styleable.LabelEditRow_android_maxLength, INVALID);
            if (maxLength != INVALID) {
                mDesc.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
            }

            CharSequence hint = styledEdit.getText(R.styleable.LabelEditRow_labelHint);
            if (!TextUtils.isEmpty(hint)) {
                mDesc.setHint(hint);
            }

            int hintColor = styledEdit.getColor(R.styleable.LabelEditRow_labelHintColor, INVALID);
            if (hintColor != INVALID) {
                mDesc.setHintTextColor(hintColor);
            }

            // 密码模式，显示密码按钮，并设置EditText的InputTpe
            if (styledEdit.getBoolean(R.styleable.LabelEditRow_labelPasswordMode, false)) {
                mDesc.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                if (inputType == EditorInfo.TYPE_CLASS_NUMBER) {
                    mDesc.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                }
            }

            styledEdit.recycle();
        }
    }
}
