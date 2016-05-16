package com.main.androiddemo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Description
 * @Author xiaojianfeng
 * @Date 16/5/6 下午1:27
 */
public class HuaBanBean {

    @SerializedName("app_name")
    private String appName;
    @SerializedName("board")
    private BoardEntity board;
    @SerializedName("pins")
    private List<PinsEntity> pins;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public List<PinsEntity> getPins() {
        return pins;
    }

    public void setPins(List<PinsEntity> pins) {
        this.pins = pins;
    }

    public static class BoardEntity {
        @SerializedName("board_id")
        private int boardId;
        @SerializedName("user_id")
        private int userId;
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;
        @SerializedName("category_id")
        private String categoryId;
        @SerializedName("seq")
        private int seq;
        @SerializedName("pin_count")
        private int pinCount;
        @SerializedName("follow_count")
        private int followCount;
        @SerializedName("like_count")
        private int likeCount;
        @SerializedName("created_at")
        private int createdAt;
        @SerializedName("updated_at")
        private int updatedAt;
        @SerializedName("deleting")
        private int deleting;
        @SerializedName("is_private")
        private int isPrivate;
        @SerializedName("rating")
        private int rating;
        @SerializedName("extra")
        private Object extra;

        public int getBoardId() {
            return boardId;
        }

        public void setBoardId(int boardId) {
            this.boardId = boardId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public int getPinCount() {
            return pinCount;
        }

        public void setPinCount(int pinCount) {
            this.pinCount = pinCount;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(int createdAt) {
            this.createdAt = createdAt;
        }

        public int getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(int updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getDeleting() {
            return deleting;
        }

        public void setDeleting(int deleting) {
            this.deleting = deleting;
        }

        public int getIsPrivate() {
            return isPrivate;
        }

        public void setIsPrivate(int isPrivate) {
            this.isPrivate = isPrivate;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public Object getExtra() {
            return extra;
        }

        public void setExtra(Object extra) {
            this.extra = extra;
        }
    }

    public static class PinsEntity {
        @SerializedName("pin_id")
        private int pinId;
        @SerializedName("user_id")
        private int userId;
        @SerializedName("board_id")
        private int boardId;
        @SerializedName("file_id")
        private int fileId;
        @SerializedName("file")
        private FileEntity file;
        @SerializedName("media_type")
        private int mediaType;
        @SerializedName("source")
        private String source;
        @SerializedName("link")
        private String link;
        @SerializedName("raw_text")
        private String rawText;
        @SerializedName("text_meta")
        private TextMetaEntity textMeta;
        @SerializedName("via")
        private int via;
        @SerializedName("via_user_id")
        private int viaUserId;
        @SerializedName("original")
        private int original;
        @SerializedName("created_at")
        private int createdAt;
        @SerializedName("like_count")
        private int likeCount;
        @SerializedName("comment_count")
        private int commentCount;
        @SerializedName("repin_count")
        private int repinCount;
        @SerializedName("is_private")
        private int isPrivate;
        @SerializedName("orig_source")
        private Object origSource;
        @SerializedName("hide_origin")
        private boolean hideOrigin;

        public int getPinId() {
            return pinId;
        }

        public void setPinId(int pinId) {
            this.pinId = pinId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getBoardId() {
            return boardId;
        }

        public void setBoardId(int boardId) {
            this.boardId = boardId;
        }

        public int getFileId() {
            return fileId;
        }

        public void setFileId(int fileId) {
            this.fileId = fileId;
        }

        public FileEntity getFile() {
            return file;
        }

        public void setFile(FileEntity file) {
            this.file = file;
        }

        public int getMediaType() {
            return mediaType;
        }

        public void setMediaType(int mediaType) {
            this.mediaType = mediaType;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getRawText() {
            return rawText;
        }

        public void setRawText(String rawText) {
            this.rawText = rawText;
        }

        public TextMetaEntity getTextMeta() {
            return textMeta;
        }

        public void setTextMeta(TextMetaEntity textMeta) {
            this.textMeta = textMeta;
        }

        public int getVia() {
            return via;
        }

        public void setVia(int via) {
            this.via = via;
        }

        public int getViaUserId() {
            return viaUserId;
        }

        public void setViaUserId(int viaUserId) {
            this.viaUserId = viaUserId;
        }

        public int getOriginal() {
            return original;
        }

        public void setOriginal(int original) {
            this.original = original;
        }

        public int getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(int createdAt) {
            this.createdAt = createdAt;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getRepinCount() {
            return repinCount;
        }

        public void setRepinCount(int repinCount) {
            this.repinCount = repinCount;
        }

        public int getIsPrivate() {
            return isPrivate;
        }

        public void setIsPrivate(int isPrivate) {
            this.isPrivate = isPrivate;
        }

        public Object getOrigSource() {
            return origSource;
        }

        public void setOrigSource(Object origSource) {
            this.origSource = origSource;
        }

        public boolean isHideOrigin() {
            return hideOrigin;
        }

        public void setHideOrigin(boolean hideOrigin) {
            this.hideOrigin = hideOrigin;
        }

        public static class FileEntity {
            @SerializedName("farm")
            private String farm;
            @SerializedName("bucket")
            private String bucket;
            @SerializedName("key")
            private String key;
            @SerializedName("type")
            private String type;
            @SerializedName("height")
            private String height;
            @SerializedName("frames")
            private String frames;
            @SerializedName("width")
            private String width;

            public String getFarm() {
                return farm;
            }

            public void setFarm(String farm) {
                this.farm = farm;
            }

            public String getBucket() {
                return bucket;
            }

            public void setBucket(String bucket) {
                this.bucket = bucket;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getFrames() {
                return frames;
            }

            public void setFrames(String frames) {
                this.frames = frames;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }
        }

        public static class TextMetaEntity {
        }
    }
}
