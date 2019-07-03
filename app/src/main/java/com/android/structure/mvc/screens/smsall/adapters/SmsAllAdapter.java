package com.android.structure.mvc.screens.smsall.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.structure.mvc.models.sms.SmsMessage;
import com.android.structure.mvc.screens.smsall.views.SmsThumbnailViewInterface;
import com.android.structure.mvc.screens.smsall.views.SmsThumbnailView;

/**
 * This adapter handles population of a list with instances of {@link SmsThumbnailViewInterface}
 */
public class SmsAllAdapter extends ArrayAdapter<SmsMessage> {

    public SmsAllAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SmsThumbnailViewInterface smsThumbnailViewMvc;
        if (convertView == null) {
            smsThumbnailViewMvc =  new SmsThumbnailView(getContext(), parent);
            /*
             Since this kind of adapters store just references to Android Views, we need to "attach"
             the entire MVC view as a tag to its root view in order to be able to retrieve it later.
             Usage of MVC view in such a way completely eliminates a need for ViewHolder.
             This is just a workaround though, the better option would be to create our own adapter
             for MVC views...
            */
            smsThumbnailViewMvc.getRootView().setTag(smsThumbnailViewMvc);
        } else {
            smsThumbnailViewMvc = ((SmsThumbnailViewInterface) convertView.getTag());
        }

        smsThumbnailViewMvc.bindSmsMessage(getItem(position));
        return smsThumbnailViewMvc.getRootView();
    }

}
