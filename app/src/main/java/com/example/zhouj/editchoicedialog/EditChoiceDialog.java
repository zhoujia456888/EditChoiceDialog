package com.example.zhouj.editchoicedialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.socks.library.KLog;

/**
 * Created by zhouj on 2016/9/26 0026.
 */

public class EditChoiceDialog extends Dialog {

    public EditChoiceDialog(Context context) {
        super(context);
    }

    public EditChoiceDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected EditChoiceDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private String title;
        EditSpinner mEditSpinner1,mEditSpinner2;
        TextView txtde;
        private int editSpinner1id1, editSpinner1id2;
        private String message,editstr1,editstr2;
        private String positiveButtonText;
        private String negativeButtonText;

        private View contentView;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        private String edittxtstr;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param title
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }


        public Builder setEditSpinner1(int editSpinner1id1) {
            this.editSpinner1id1 = editSpinner1id1;
            return this;
        }

        public Builder setEditSpinnerStr1(String editstr1) {
            this.editstr1= editstr1;
            return this;
        }


        public Builder setEditSpinner2(int editSpinner1id2) {
            this.editSpinner1id2 = editSpinner1id2;
            return this;
        }

        public Builder setEditSpinnerStr2(String editstr2) {
            this.editstr2 = editstr2;
            return this;
        }


        public String getedittxtstr(){
            return edittxtstr;
        }


        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public EditChoiceDialog create() {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final EditChoiceDialog dialog = new EditChoiceDialog(context, R.style.Dialog);
            final View layout = inflater.inflate(R.layout.edit_choice_dialog_layout, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            //设置标题文字
            ((TextView) layout.findViewById(R.id.txt_title)).setText(title);

            txtde=(TextView) layout.findViewById(R.id.de);

            //设置EditSpinner1内容
            mEditSpinner1 = (EditSpinner) layout.findViewById(R.id.ed_firstword);
            mEditSpinner1.setText(editstr1);
            ListAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,
                    context.getResources().getStringArray(editSpinner1id1));
            mEditSpinner1.setAdapter(adapter);
            mEditSpinner1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    KLog.d( "mEditSpinner1 popup window dismissed! ");
                }
            });

            mEditSpinner1.setOnShowListener(new EditSpinner.OnShowListener() {
                @Override
                public void onShow() {
                    // maybe you want to hide the soft input panel when the popup window is showing.
                    hideSoftInputPanel();
                }
            });

            //设置EditSpinner2内容
            mEditSpinner2= (EditSpinner) layout.findViewById(R.id.ed_secondword);
            mEditSpinner2.setText(editstr2);
            ListAdapter adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,
                    context.getResources().getStringArray(editSpinner1id2));
            mEditSpinner2.setAdapter(adapter2);

            mEditSpinner2.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    KLog.d( "mEditSpinner2 popup window dismissed! ");
                }
            });

            mEditSpinner2.setOnShowListener(new EditSpinner.OnShowListener() {
                @Override
                public void onShow() {
                    hideSoftInputPanel();
                }
            });

                //设置确认按钮
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.confirm))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.confirm))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);

                                    String str1 = mEditSpinner1.getText().toString();
                                    String str2=((TextView)layout.findViewById(R.id.de)).getText().toString();
                                    String str3 = mEditSpinner2.getText().toString();

                                    edittxtstr=str1+str2+str3;



                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.confirm).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.cancel))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.cancel))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.cancel).setVisibility(
                        View.GONE);
            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.txt_content)).setText(message);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.ly_txt_content))
                        .removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.ly_txt_content))
                        .addView(contentView, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
            }

            dialog.setContentView(layout);
            return dialog;
        }

        private void hideSoftInputPanel() {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(mEditSpinner1.getWindowToken(), 0);
            }
        }
    }
}
