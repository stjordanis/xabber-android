package com.xabber.android.presentation.ui.contactlist.viewobjects;

import android.graphics.drawable.Drawable;

import com.xabber.android.data.entity.AccountJid;
import com.xabber.android.data.roster.GroupManager;
import com.xabber.android.ui.adapter.contactlist.AccountConfiguration;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.items.IExpandable;

/**
 * Created by valery.miller on 07.02.18.
 */

public class AccountWithContactsVO extends AccountVO implements IExpandable<AccountVO.ViewHolder, ContactVO> {

    private boolean mExpanded = true;
    private List<ContactVO> mSubItems;

    public AccountWithContactsVO(int accountColorIndicator, boolean showOfflineShadow, String name,
                                 String jid, String status, int statusLevel, int statusId, Drawable avatar,
                                 int offlineModeLevel, String contactCount, AccountJid accountJid,
                                 boolean isExpand, String groupName, AccountClickListener listener) {

        super(accountColorIndicator, showOfflineShadow, name, jid, status, statusLevel, statusId,
                avatar, offlineModeLevel, contactCount, accountJid, isExpand, groupName, listener);

        mExpanded = isExpand;
    }

    @Override
    public boolean isExpanded() {
        return mExpanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        mExpanded = expanded;
        GroupManager.getInstance().setExpanded(getAccountJid(), getGroupName(), mExpanded);
    }

    @Override
    public int getExpansionLevel() {
        return 0;
    }

    @Override
    public List<ContactVO> getSubItems() {
        return mSubItems;
    }

    public void addSubItem(ContactVO subItem) {
        if (mSubItems == null)
            mSubItems = new ArrayList<>();
        mSubItems.add(subItem);
    }

    public static AccountWithContactsVO convert(AccountConfiguration configuration, AccountClickListener listener) {
        AccountVO contactVO = AccountVO.convert(configuration, listener);
        return new AccountWithContactsVO(
                contactVO.getAccountColorIndicator(), contactVO.isShowOfflineShadow(),
                contactVO.getName(), contactVO.getJid(), contactVO.getStatus(),
                contactVO.getStatusLevel(), contactVO.getStatusId(), contactVO.getAvatar(),
                contactVO.getOfflineModeLevel(), contactVO.getContactCount(),
                contactVO.getAccountJid(), contactVO.isExpand(), contactVO.getGroupName(), contactVO.listener);
    }
}
