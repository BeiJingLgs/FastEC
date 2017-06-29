package com.flj.latte.ec.main.personal.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.diabin.latte.ec.R;

import java.util.List;

/**
 * Created by 傅令杰
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {


    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case 20:
                helper.setText(R.id.tv_arrow_text, item.getmText());
                helper.setText(R.id.tv_arrow_value, item.getmValue());
                break;
            default:
                break;
        }
    }
}
