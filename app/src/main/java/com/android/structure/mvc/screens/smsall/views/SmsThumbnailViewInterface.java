package com.android.structure.mvc.screens.smsall.views;

import com.android.structure.mvc.models.sms.SmsMessage;
import com.android.structure.mvc.screens.common.views.BaseViewInterface;

/**
 * This interface corresponds to a single SMS preview element (thumbnail) which is intended to
 * be used in a list of similar elements
 */
public interface SmsThumbnailViewInterface extends BaseViewInterface {

    /**
     * Show thumbnail of a particular SMS message
     * @param smsMessage the message to show
     */
    void bindSmsMessage(SmsMessage smsMessage);
}
