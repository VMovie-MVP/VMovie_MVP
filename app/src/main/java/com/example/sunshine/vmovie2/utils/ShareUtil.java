package com.example.sunshine.vmovie2.utils;

import android.content.Context;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dell on 2017/3/31.
 */

public class ShareUtil {

    public static void showShare(Context context, String title, String titleUrl, String content, String imageUrl, String comment, String siteName, String siteUrl) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        if (title != null) {
            oks.setTitle(title);
        }
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        if (titleUrl != null) {
            oks.setTitleUrl(titleUrl);
        }
        // text是分享文本，所有平台都需要这个字段
        if (content != null) {
            oks.setText(content);
        }
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        if (imageUrl != null) {
            oks.setImageUrl(imageUrl);
        }

//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        if (localImagePath != null) {
//            oks.setImagePath(localImagePath);//确保SDcard下面存在此张图片
//        }


//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        if (comment != null) {
            oks.setComment(comment);
        }

        // site是分享此内容的网站名称，仅在QQ空间使用
        if (siteName != null) {
            oks.setSite("ShareSDK");
        }

        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        if (siteUrl != null) {
            oks.setSiteUrl(siteUrl);
        }

// 启动分享GUI
        oks.show(context);
    }

}
