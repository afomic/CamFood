package afomic.com.camfood.viewHelper.fundWallet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import afomic.com.camfood.R;

public class FundWalletDialog extends DialogFragment {
    private TextView dialogTitleTextView;
    private EditText amountEditText;
    private Button fundWalletButton;
    public static final int TYPE_WITHDRAW_FUND = 0;
    public static final int TYPE_DEPOSIT_FUND = 2;
    private static final String EXTRA_TYPE = "type";

    private int dialogType;
    private FundWalletInteractor interactor;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interactor = (FundWalletInteractor) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactor = null;
    }

    public static FundWalletDialog newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_TYPE, type);
        FundWalletDialog fundWalletDialog = new FundWalletDialog();
        fundWalletDialog.setArguments(args);
        return fundWalletDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogType = getArguments().getInt(EXTRA_TYPE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_fund_wallet, null, false);
        builder.setView(view);
        dialogTitleTextView = view.findViewById(R.id.tv_fund_wallet_title);
        amountEditText = view.findViewById(R.id.edt_amount);
        fundWalletButton = view.findViewById(R.id.btn_fund_wallet);
        if (dialogType == TYPE_WITHDRAW_FUND) {
            dialogTitleTextView.setText(getString(R.string.withdraw_fund));
            fundWalletButton.setText(R.string.withdraw_fund);
            fundWalletButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        fundWalletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = amountEditText.getText().toString();
                int amountInt = Integer.parseInt(amount);
                if (dialogType == TYPE_DEPOSIT_FUND) {
                    interactor.onFundWallet(amountInt);
                } else {
                    interactor.onWithdraw(amountInt);
                }
                dismiss();
            }
        });
        return builder.create();
    }

    public interface FundWalletInteractor {
        void onFundWallet(int amount);

        void onWithdraw(int amount);
    }
}
