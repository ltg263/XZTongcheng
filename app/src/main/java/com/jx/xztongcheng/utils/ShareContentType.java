package com.jx.xztongcheng.utils;

import androidx.annotation.StringDef;

/**
 * Create by Sxl on 2020/12/7.
 */
@StringDef({ShareContentType.TEXT, ShareContentType.IMAGE,
        ShareContentType.AUDIO, ShareContentType.VIDEO, ShareContentType.FILE})
public @interface ShareContentType {
    /**
     * Share Text
     */
    final String TEXT = "text/plain";

    /**
     * Share Image
     */
    final String IMAGE = "image/*";

    /**
     * Share Audio
     */
    final String AUDIO = "audio/*";

    /**
     * Share Video
     */
    final String VIDEO = "video/*";

    /**
     * Share File
     */
    final String FILE = "*/*";
}
