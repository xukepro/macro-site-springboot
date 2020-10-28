// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ChatMsgRes.proto

package com.xuke.macrosite.common.protobuf;

public final class ChatMsgResProto {
  private ChatMsgResProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ChatMsgResOrBuilder extends
      // @@protoc_insertion_point(interface_extends:protocol.ChatMsgRes)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 type = 1;</code>
     * @return The type.
     */
    int getType();

    /**
     * <code>int32 uid = 2;</code>
     * @return The uid.
     */
    int getUid();

    /**
     * <code>.protocol.ChatContentRes chat_content = 3;</code>
     * @return Whether the chatContent field is set.
     */
    boolean hasChatContent();
    /**
     * <code>.protocol.ChatContentRes chat_content = 3;</code>
     * @return The chatContent.
     */
    com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes getChatContent();
    /**
     * <code>.protocol.ChatContentRes chat_content = 3;</code>
     */
    com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentResOrBuilder getChatContentOrBuilder();

    /**
     * <code>string send_time = 4;</code>
     * @return The sendTime.
     */
    String getSendTime();
    /**
     * <code>string send_time = 4;</code>
     * @return The bytes for sendTime.
     */
    com.google.protobuf.ByteString
        getSendTimeBytes();
  }
  /**
   * <pre>
   * 返回实体
   * </pre>
   *
   * Protobuf type {@code protocol.ChatMsgRes}
   */
  public static final class ChatMsgRes extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:protocol.ChatMsgRes)
      ChatMsgResOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ChatMsgRes.newBuilder() to construct.
    private ChatMsgRes(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ChatMsgRes() {
      sendTime_ = "";
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(
        UnusedPrivateParameter unused) {
      return new ChatMsgRes();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ChatMsgRes(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              type_ = input.readInt32();
              break;
            }
            case 16: {

              uid_ = input.readInt32();
              break;
            }
            case 26: {
              com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.Builder subBuilder = null;
              if (chatContent_ != null) {
                subBuilder = chatContent_.toBuilder();
              }
              chatContent_ = input.readMessage(com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(chatContent_);
                chatContent_ = subBuilder.buildPartial();
              }

              break;
            }
            case 34: {
              String s = input.readStringRequireUtf8();

              sendTime_ = s;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ChatMsgResProto.internal_static_protocol_ChatMsgRes_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ChatMsgResProto.internal_static_protocol_ChatMsgRes_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ChatMsgRes.class, Builder.class);
    }

    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;
    /**
     * <code>int32 type = 1;</code>
     * @return The type.
     */
    @Override
    public int getType() {
      return type_;
    }

    public static final int UID_FIELD_NUMBER = 2;
    private int uid_;
    /**
     * <code>int32 uid = 2;</code>
     * @return The uid.
     */
    @Override
    public int getUid() {
      return uid_;
    }

    public static final int CHAT_CONTENT_FIELD_NUMBER = 3;
    private com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes chatContent_;
    /**
     * <code>.protocol.ChatContentRes chat_content = 3;</code>
     * @return Whether the chatContent field is set.
     */
    @Override
    public boolean hasChatContent() {
      return chatContent_ != null;
    }
    /**
     * <code>.protocol.ChatContentRes chat_content = 3;</code>
     * @return The chatContent.
     */
    @Override
    public com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes getChatContent() {
      return chatContent_ == null ? com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.getDefaultInstance() : chatContent_;
    }
    /**
     * <code>.protocol.ChatContentRes chat_content = 3;</code>
     */
    @Override
    public com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentResOrBuilder getChatContentOrBuilder() {
      return getChatContent();
    }

    public static final int SEND_TIME_FIELD_NUMBER = 4;
    private volatile Object sendTime_;
    /**
     * <code>string send_time = 4;</code>
     * @return The sendTime.
     */
    @Override
    public String getSendTime() {
      Object ref = sendTime_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        sendTime_ = s;
        return s;
      }
    }
    /**
     * <code>string send_time = 4;</code>
     * @return The bytes for sendTime.
     */
    @Override
    public com.google.protobuf.ByteString
        getSendTimeBytes() {
      Object ref = sendTime_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        sendTime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (type_ != 0) {
        output.writeInt32(1, type_);
      }
      if (uid_ != 0) {
        output.writeInt32(2, uid_);
      }
      if (chatContent_ != null) {
        output.writeMessage(3, getChatContent());
      }
      if (!getSendTimeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, sendTime_);
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, type_);
      }
      if (uid_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, uid_);
      }
      if (chatContent_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, getChatContent());
      }
      if (!getSendTimeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, sendTime_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof ChatMsgRes)) {
        return super.equals(obj);
      }
      ChatMsgRes other = (ChatMsgRes) obj;

      if (getType()
          != other.getType()) return false;
      if (getUid()
          != other.getUid()) return false;
      if (hasChatContent() != other.hasChatContent()) return false;
      if (hasChatContent()) {
        if (!getChatContent()
            .equals(other.getChatContent())) return false;
      }
      if (!getSendTime()
          .equals(other.getSendTime())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (37 * hash) + UID_FIELD_NUMBER;
      hash = (53 * hash) + getUid();
      if (hasChatContent()) {
        hash = (37 * hash) + CHAT_CONTENT_FIELD_NUMBER;
        hash = (53 * hash) + getChatContent().hashCode();
      }
      hash = (37 * hash) + SEND_TIME_FIELD_NUMBER;
      hash = (53 * hash) + getSendTime().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ChatMsgRes parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ChatMsgRes parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ChatMsgRes parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ChatMsgRes parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ChatMsgRes parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ChatMsgRes parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ChatMsgRes parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ChatMsgRes parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ChatMsgRes parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ChatMsgRes parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ChatMsgRes parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ChatMsgRes parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(ChatMsgRes prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * 返回实体
     * </pre>
     *
     * Protobuf type {@code protocol.ChatMsgRes}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:protocol.ChatMsgRes)
        ChatMsgResOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ChatMsgResProto.internal_static_protocol_ChatMsgRes_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ChatMsgResProto.internal_static_protocol_ChatMsgRes_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ChatMsgRes.class, Builder.class);
      }

      // Construct using com.xuke.macrosite.common.protobuf.ChatMsgResProto.ChatMsgRes.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        type_ = 0;

        uid_ = 0;

        if (chatContentBuilder_ == null) {
          chatContent_ = null;
        } else {
          chatContent_ = null;
          chatContentBuilder_ = null;
        }
        sendTime_ = "";

        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ChatMsgResProto.internal_static_protocol_ChatMsgRes_descriptor;
      }

      @Override
      public ChatMsgRes getDefaultInstanceForType() {
        return ChatMsgRes.getDefaultInstance();
      }

      @Override
      public ChatMsgRes build() {
        ChatMsgRes result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public ChatMsgRes buildPartial() {
        ChatMsgRes result = new ChatMsgRes(this);
        result.type_ = type_;
        result.uid_ = uid_;
        if (chatContentBuilder_ == null) {
          result.chatContent_ = chatContent_;
        } else {
          result.chatContent_ = chatContentBuilder_.build();
        }
        result.sendTime_ = sendTime_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof ChatMsgRes) {
          return mergeFrom((ChatMsgRes)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ChatMsgRes other) {
        if (other == ChatMsgRes.getDefaultInstance()) return this;
        if (other.getType() != 0) {
          setType(other.getType());
        }
        if (other.getUid() != 0) {
          setUid(other.getUid());
        }
        if (other.hasChatContent()) {
          mergeChatContent(other.getChatContent());
        }
        if (!other.getSendTime().isEmpty()) {
          sendTime_ = other.sendTime_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        ChatMsgRes parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ChatMsgRes) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int type_ ;
      /**
       * <code>int32 type = 1;</code>
       * @return The type.
       */
      @Override
      public int getType() {
        return type_;
      }
      /**
       * <code>int32 type = 1;</code>
       * @param value The type to set.
       * @return This builder for chaining.
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 type = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearType() {
        
        type_ = 0;
        onChanged();
        return this;
      }

      private int uid_ ;
      /**
       * <code>int32 uid = 2;</code>
       * @return The uid.
       */
      @Override
      public int getUid() {
        return uid_;
      }
      /**
       * <code>int32 uid = 2;</code>
       * @param value The uid to set.
       * @return This builder for chaining.
       */
      public Builder setUid(int value) {
        
        uid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 uid = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearUid() {
        
        uid_ = 0;
        onChanged();
        return this;
      }

      private com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes chatContent_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes, com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.Builder, com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentResOrBuilder> chatContentBuilder_;
      /**
       * <code>.protocol.ChatContentRes chat_content = 3;</code>
       * @return Whether the chatContent field is set.
       */
      public boolean hasChatContent() {
        return chatContentBuilder_ != null || chatContent_ != null;
      }
      /**
       * <code>.protocol.ChatContentRes chat_content = 3;</code>
       * @return The chatContent.
       */
      public com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes getChatContent() {
        if (chatContentBuilder_ == null) {
          return chatContent_ == null ? com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.getDefaultInstance() : chatContent_;
        } else {
          return chatContentBuilder_.getMessage();
        }
      }
      /**
       * <code>.protocol.ChatContentRes chat_content = 3;</code>
       */
      public Builder setChatContent(com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes value) {
        if (chatContentBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          chatContent_ = value;
          onChanged();
        } else {
          chatContentBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.protocol.ChatContentRes chat_content = 3;</code>
       */
      public Builder setChatContent(
          com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.Builder builderForValue) {
        if (chatContentBuilder_ == null) {
          chatContent_ = builderForValue.build();
          onChanged();
        } else {
          chatContentBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.protocol.ChatContentRes chat_content = 3;</code>
       */
      public Builder mergeChatContent(com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes value) {
        if (chatContentBuilder_ == null) {
          if (chatContent_ != null) {
            chatContent_ =
              com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.newBuilder(chatContent_).mergeFrom(value).buildPartial();
          } else {
            chatContent_ = value;
          }
          onChanged();
        } else {
          chatContentBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.protocol.ChatContentRes chat_content = 3;</code>
       */
      public Builder clearChatContent() {
        if (chatContentBuilder_ == null) {
          chatContent_ = null;
          onChanged();
        } else {
          chatContent_ = null;
          chatContentBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.protocol.ChatContentRes chat_content = 3;</code>
       */
      public com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.Builder getChatContentBuilder() {
        
        onChanged();
        return getChatContentFieldBuilder().getBuilder();
      }
      /**
       * <code>.protocol.ChatContentRes chat_content = 3;</code>
       */
      public com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentResOrBuilder getChatContentOrBuilder() {
        if (chatContentBuilder_ != null) {
          return chatContentBuilder_.getMessageOrBuilder();
        } else {
          return chatContent_ == null ?
              com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.getDefaultInstance() : chatContent_;
        }
      }
      /**
       * <code>.protocol.ChatContentRes chat_content = 3;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes, com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.Builder, com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentResOrBuilder> 
          getChatContentFieldBuilder() {
        if (chatContentBuilder_ == null) {
          chatContentBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes, com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentRes.Builder, com.xuke.macrosite.common.protobuf.ChatContentResProto.ChatContentResOrBuilder>(
                  getChatContent(),
                  getParentForChildren(),
                  isClean());
          chatContent_ = null;
        }
        return chatContentBuilder_;
      }

      private Object sendTime_ = "";
      /**
       * <code>string send_time = 4;</code>
       * @return The sendTime.
       */
      public String getSendTime() {
        Object ref = sendTime_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          sendTime_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string send_time = 4;</code>
       * @return The bytes for sendTime.
       */
      public com.google.protobuf.ByteString
          getSendTimeBytes() {
        Object ref = sendTime_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          sendTime_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string send_time = 4;</code>
       * @param value The sendTime to set.
       * @return This builder for chaining.
       */
      public Builder setSendTime(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        sendTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string send_time = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearSendTime() {
        
        sendTime_ = getDefaultInstance().getSendTime();
        onChanged();
        return this;
      }
      /**
       * <code>string send_time = 4;</code>
       * @param value The bytes for sendTime to set.
       * @return This builder for chaining.
       */
      public Builder setSendTimeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        sendTime_ = value;
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:protocol.ChatMsgRes)
    }

    // @@protoc_insertion_point(class_scope:protocol.ChatMsgRes)
    private static final ChatMsgRes DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ChatMsgRes();
    }

    public static ChatMsgRes getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ChatMsgRes>
        PARSER = new com.google.protobuf.AbstractParser<ChatMsgRes>() {
      @Override
      public ChatMsgRes parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ChatMsgRes(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ChatMsgRes> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<ChatMsgRes> getParserForType() {
      return PARSER;
    }

    @Override
    public ChatMsgRes getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protocol_ChatMsgRes_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protocol_ChatMsgRes_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\020ChatMsgRes.proto\022\010protocol\032\024ChatConten" +
      "tRes.proto\"j\n\nChatMsgRes\022\014\n\004type\030\001 \001(\005\022\013" +
      "\n\003uid\030\002 \001(\005\022.\n\014chat_content\030\003 \001(\0132\030.prot" +
      "ocol.ChatContentRes\022\021\n\tsend_time\030\004 \001(\tB7" +
      "\n\"com.xuke.macrosite.common.protobufB\017Ch" +
      "atMsgResProtoH\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.xuke.macrosite.common.protobuf.ChatContentResProto.getDescriptor(),
        });
    internal_static_protocol_ChatMsgRes_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_protocol_ChatMsgRes_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protocol_ChatMsgRes_descriptor,
        new String[] { "Type", "Uid", "ChatContent", "SendTime", });
    com.xuke.macrosite.common.protobuf.ChatContentResProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}