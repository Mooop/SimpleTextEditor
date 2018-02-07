package com.mop.simple.texteditor.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.mop.simple.texteditor.db.DraftDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Administrator on 2018/2/2.
 */
@Table(database = DraftDatabase.class)
public class DraftData extends BaseModel implements Parcelable {
    public static final Creator<DraftData> CREATOR = new Creator<DraftData>() {
        @Override
        public DraftData createFromParcel(Parcel in) {
            return new DraftData(in);
        }

        @Override
        public DraftData[] newArray(int size) {
            return new DraftData[size];
        }
    };
    /**
     * 草稿ID,ID自增
     */
    @PrimaryKey(autoincrement = true)
    public int id;
    /**
     * 草稿标题
     */
    @Column
    public String title;
    /**
     * 草稿内容
     */
    @Column
    public String content;
    /**
     * 草稿类型，1纯文本，2Html
     */
    @Column
    public int type;
    /**
     * 是否加密，0未加密，1加密
     */
    @Column
    public int isEncrypt;
    /**
     * 创建时间
     */
    @Column
    public String createTime;
    /**
     * 修改时间
     */
    @Column
    public String updateTime;
    /**
     * 用户ID
     */
    @Column
    public String userId;
    /**
     * 用户名
     */
    @Column
    public String userName;

    public DraftData() {
    }

    protected DraftData(Parcel in) {
        id = in.readInt();
        title = in.readString();
        content = in.readString();
        type = in.readInt();
        isEncrypt = in.readInt();
        createTime = in.readString();
        updateTime = in.readString();
        userId = in.readString();
        userName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(type);
        dest.writeInt(isEncrypt);
        dest.writeString(createTime);
        dest.writeString(updateTime);
        dest.writeString(userId);
        dest.writeString(userName);
    }
}
