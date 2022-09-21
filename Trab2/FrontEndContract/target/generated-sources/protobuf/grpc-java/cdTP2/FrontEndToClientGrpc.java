package cdTP2;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The Chat service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.28.1)",
    comments = "Source: chat.proto")
public final class FrontEndToClientGrpc {

  private FrontEndToClientGrpc() {}

  public static final String SERVICE_NAME = "forum.FrontEndToClient";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      cdTP2.Event> getGetHighestSpeedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getHighestSpeed",
      requestType = com.google.protobuf.Empty.class,
      responseType = cdTP2.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      cdTP2.Event> getGetHighestSpeedMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, cdTP2.Event> getGetHighestSpeedMethod;
    if ((getGetHighestSpeedMethod = FrontEndToClientGrpc.getGetHighestSpeedMethod) == null) {
      synchronized (FrontEndToClientGrpc.class) {
        if ((getGetHighestSpeedMethod = FrontEndToClientGrpc.getGetHighestSpeedMethod) == null) {
          FrontEndToClientGrpc.getGetHighestSpeedMethod = getGetHighestSpeedMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, cdTP2.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getHighestSpeed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Event.getDefaultInstance()))
              .setSchemaDescriptor(new FrontEndToClientMethodDescriptorSupplier("getHighestSpeed"))
              .build();
        }
      }
    }
    return getGetHighestSpeedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cdTP2.Filter,
      cdTP2.Event> getGetSpeedFromDateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSpeedFromDate",
      requestType = cdTP2.Filter.class,
      responseType = cdTP2.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cdTP2.Filter,
      cdTP2.Event> getGetSpeedFromDateMethod() {
    io.grpc.MethodDescriptor<cdTP2.Filter, cdTP2.Event> getGetSpeedFromDateMethod;
    if ((getGetSpeedFromDateMethod = FrontEndToClientGrpc.getGetSpeedFromDateMethod) == null) {
      synchronized (FrontEndToClientGrpc.class) {
        if ((getGetSpeedFromDateMethod = FrontEndToClientGrpc.getGetSpeedFromDateMethod) == null) {
          FrontEndToClientGrpc.getGetSpeedFromDateMethod = getGetSpeedFromDateMethod =
              io.grpc.MethodDescriptor.<cdTP2.Filter, cdTP2.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSpeedFromDate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Filter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Event.getDefaultInstance()))
              .setSchemaDescriptor(new FrontEndToClientMethodDescriptorSupplier("getSpeedFromDate"))
              .build();
        }
      }
    }
    return getGetSpeedFromDateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cdTP2.Filter,
      cdTP2.Events> getGetSpeedIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSpeedID",
      requestType = cdTP2.Filter.class,
      responseType = cdTP2.Events.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cdTP2.Filter,
      cdTP2.Events> getGetSpeedIDMethod() {
    io.grpc.MethodDescriptor<cdTP2.Filter, cdTP2.Events> getGetSpeedIDMethod;
    if ((getGetSpeedIDMethod = FrontEndToClientGrpc.getGetSpeedIDMethod) == null) {
      synchronized (FrontEndToClientGrpc.class) {
        if ((getGetSpeedIDMethod = FrontEndToClientGrpc.getGetSpeedIDMethod) == null) {
          FrontEndToClientGrpc.getGetSpeedIDMethod = getGetSpeedIDMethod =
              io.grpc.MethodDescriptor.<cdTP2.Filter, cdTP2.Events>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSpeedID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Filter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Events.getDefaultInstance()))
              .setSchemaDescriptor(new FrontEndToClientMethodDescriptorSupplier("getSpeedID"))
              .build();
        }
      }
    }
    return getGetSpeedIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cdTP2.Filter,
      cdTP2.Events> getGetSpeedFromLocalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSpeedFromLocal",
      requestType = cdTP2.Filter.class,
      responseType = cdTP2.Events.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cdTP2.Filter,
      cdTP2.Events> getGetSpeedFromLocalMethod() {
    io.grpc.MethodDescriptor<cdTP2.Filter, cdTP2.Events> getGetSpeedFromLocalMethod;
    if ((getGetSpeedFromLocalMethod = FrontEndToClientGrpc.getGetSpeedFromLocalMethod) == null) {
      synchronized (FrontEndToClientGrpc.class) {
        if ((getGetSpeedFromLocalMethod = FrontEndToClientGrpc.getGetSpeedFromLocalMethod) == null) {
          FrontEndToClientGrpc.getGetSpeedFromLocalMethod = getGetSpeedFromLocalMethod =
              io.grpc.MethodDescriptor.<cdTP2.Filter, cdTP2.Events>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSpeedFromLocal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Filter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Events.getDefaultInstance()))
              .setSchemaDescriptor(new FrontEndToClientMethodDescriptorSupplier("getSpeedFromLocal"))
              .build();
        }
      }
    }
    return getGetSpeedFromLocalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cdTP2.Filter,
      cdTP2.Events> getGetSpeedFromSpeedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSpeedFromSpeed",
      requestType = cdTP2.Filter.class,
      responseType = cdTP2.Events.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cdTP2.Filter,
      cdTP2.Events> getGetSpeedFromSpeedMethod() {
    io.grpc.MethodDescriptor<cdTP2.Filter, cdTP2.Events> getGetSpeedFromSpeedMethod;
    if ((getGetSpeedFromSpeedMethod = FrontEndToClientGrpc.getGetSpeedFromSpeedMethod) == null) {
      synchronized (FrontEndToClientGrpc.class) {
        if ((getGetSpeedFromSpeedMethod = FrontEndToClientGrpc.getGetSpeedFromSpeedMethod) == null) {
          FrontEndToClientGrpc.getGetSpeedFromSpeedMethod = getGetSpeedFromSpeedMethod =
              io.grpc.MethodDescriptor.<cdTP2.Filter, cdTP2.Events>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSpeedFromSpeed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Filter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Events.getDefaultInstance()))
              .setSchemaDescriptor(new FrontEndToClientMethodDescriptorSupplier("getSpeedFromSpeed"))
              .build();
        }
      }
    }
    return getGetSpeedFromSpeedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      cdTP2.Consumers> getGetConsumersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getConsumers",
      requestType = com.google.protobuf.Empty.class,
      responseType = cdTP2.Consumers.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      cdTP2.Consumers> getGetConsumersMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, cdTP2.Consumers> getGetConsumersMethod;
    if ((getGetConsumersMethod = FrontEndToClientGrpc.getGetConsumersMethod) == null) {
      synchronized (FrontEndToClientGrpc.class) {
        if ((getGetConsumersMethod = FrontEndToClientGrpc.getGetConsumersMethod) == null) {
          FrontEndToClientGrpc.getGetConsumersMethod = getGetConsumersMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, cdTP2.Consumers>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getConsumers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cdTP2.Consumers.getDefaultInstance()))
              .setSchemaDescriptor(new FrontEndToClientMethodDescriptorSupplier("getConsumers"))
              .build();
        }
      }
    }
    return getGetConsumersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FrontEndToClientStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FrontEndToClientStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FrontEndToClientStub>() {
        @java.lang.Override
        public FrontEndToClientStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FrontEndToClientStub(channel, callOptions);
        }
      };
    return FrontEndToClientStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FrontEndToClientBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FrontEndToClientBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FrontEndToClientBlockingStub>() {
        @java.lang.Override
        public FrontEndToClientBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FrontEndToClientBlockingStub(channel, callOptions);
        }
      };
    return FrontEndToClientBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FrontEndToClientFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FrontEndToClientFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FrontEndToClientFutureStub>() {
        @java.lang.Override
        public FrontEndToClientFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FrontEndToClientFutureStub(channel, callOptions);
        }
      };
    return FrontEndToClientFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The Chat service definition.
   * </pre>
   */
  public static abstract class FrontEndToClientImplBase implements io.grpc.BindableService {

    /**
     */
    public void getHighestSpeed(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<cdTP2.Event> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHighestSpeedMethod(), responseObserver);
    }

    /**
     */
    public void getSpeedFromDate(cdTP2.Filter request,
        io.grpc.stub.StreamObserver<cdTP2.Event> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSpeedFromDateMethod(), responseObserver);
    }

    /**
     */
    public void getSpeedID(cdTP2.Filter request,
        io.grpc.stub.StreamObserver<cdTP2.Events> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSpeedIDMethod(), responseObserver);
    }

    /**
     */
    public void getSpeedFromLocal(cdTP2.Filter request,
        io.grpc.stub.StreamObserver<cdTP2.Events> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSpeedFromLocalMethod(), responseObserver);
    }

    /**
     */
    public void getSpeedFromSpeed(cdTP2.Filter request,
        io.grpc.stub.StreamObserver<cdTP2.Events> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSpeedFromSpeedMethod(), responseObserver);
    }

    /**
     */
    public void getConsumers(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<cdTP2.Consumers> responseObserver) {
      asyncUnimplementedUnaryCall(getGetConsumersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetHighestSpeedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                cdTP2.Event>(
                  this, METHODID_GET_HIGHEST_SPEED)))
          .addMethod(
            getGetSpeedFromDateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cdTP2.Filter,
                cdTP2.Event>(
                  this, METHODID_GET_SPEED_FROM_DATE)))
          .addMethod(
            getGetSpeedIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cdTP2.Filter,
                cdTP2.Events>(
                  this, METHODID_GET_SPEED_ID)))
          .addMethod(
            getGetSpeedFromLocalMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cdTP2.Filter,
                cdTP2.Events>(
                  this, METHODID_GET_SPEED_FROM_LOCAL)))
          .addMethod(
            getGetSpeedFromSpeedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cdTP2.Filter,
                cdTP2.Events>(
                  this, METHODID_GET_SPEED_FROM_SPEED)))
          .addMethod(
            getGetConsumersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                cdTP2.Consumers>(
                  this, METHODID_GET_CONSUMERS)))
          .build();
    }
  }

  /**
   * <pre>
   * The Chat service definition.
   * </pre>
   */
  public static final class FrontEndToClientStub extends io.grpc.stub.AbstractAsyncStub<FrontEndToClientStub> {
    private FrontEndToClientStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FrontEndToClientStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FrontEndToClientStub(channel, callOptions);
    }

    /**
     */
    public void getHighestSpeed(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<cdTP2.Event> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHighestSpeedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSpeedFromDate(cdTP2.Filter request,
        io.grpc.stub.StreamObserver<cdTP2.Event> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSpeedFromDateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSpeedID(cdTP2.Filter request,
        io.grpc.stub.StreamObserver<cdTP2.Events> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSpeedIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSpeedFromLocal(cdTP2.Filter request,
        io.grpc.stub.StreamObserver<cdTP2.Events> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSpeedFromLocalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSpeedFromSpeed(cdTP2.Filter request,
        io.grpc.stub.StreamObserver<cdTP2.Events> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSpeedFromSpeedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getConsumers(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<cdTP2.Consumers> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetConsumersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The Chat service definition.
   * </pre>
   */
  public static final class FrontEndToClientBlockingStub extends io.grpc.stub.AbstractBlockingStub<FrontEndToClientBlockingStub> {
    private FrontEndToClientBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FrontEndToClientBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FrontEndToClientBlockingStub(channel, callOptions);
    }

    /**
     */
    public cdTP2.Event getHighestSpeed(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetHighestSpeedMethod(), getCallOptions(), request);
    }

    /**
     */
    public cdTP2.Event getSpeedFromDate(cdTP2.Filter request) {
      return blockingUnaryCall(
          getChannel(), getGetSpeedFromDateMethod(), getCallOptions(), request);
    }

    /**
     */
    public cdTP2.Events getSpeedID(cdTP2.Filter request) {
      return blockingUnaryCall(
          getChannel(), getGetSpeedIDMethod(), getCallOptions(), request);
    }

    /**
     */
    public cdTP2.Events getSpeedFromLocal(cdTP2.Filter request) {
      return blockingUnaryCall(
          getChannel(), getGetSpeedFromLocalMethod(), getCallOptions(), request);
    }

    /**
     */
    public cdTP2.Events getSpeedFromSpeed(cdTP2.Filter request) {
      return blockingUnaryCall(
          getChannel(), getGetSpeedFromSpeedMethod(), getCallOptions(), request);
    }

    /**
     */
    public cdTP2.Consumers getConsumers(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetConsumersMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The Chat service definition.
   * </pre>
   */
  public static final class FrontEndToClientFutureStub extends io.grpc.stub.AbstractFutureStub<FrontEndToClientFutureStub> {
    private FrontEndToClientFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FrontEndToClientFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FrontEndToClientFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cdTP2.Event> getHighestSpeed(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHighestSpeedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cdTP2.Event> getSpeedFromDate(
        cdTP2.Filter request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSpeedFromDateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cdTP2.Events> getSpeedID(
        cdTP2.Filter request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSpeedIDMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cdTP2.Events> getSpeedFromLocal(
        cdTP2.Filter request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSpeedFromLocalMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cdTP2.Events> getSpeedFromSpeed(
        cdTP2.Filter request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSpeedFromSpeedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cdTP2.Consumers> getConsumers(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetConsumersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_HIGHEST_SPEED = 0;
  private static final int METHODID_GET_SPEED_FROM_DATE = 1;
  private static final int METHODID_GET_SPEED_ID = 2;
  private static final int METHODID_GET_SPEED_FROM_LOCAL = 3;
  private static final int METHODID_GET_SPEED_FROM_SPEED = 4;
  private static final int METHODID_GET_CONSUMERS = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FrontEndToClientImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FrontEndToClientImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_HIGHEST_SPEED:
          serviceImpl.getHighestSpeed((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<cdTP2.Event>) responseObserver);
          break;
        case METHODID_GET_SPEED_FROM_DATE:
          serviceImpl.getSpeedFromDate((cdTP2.Filter) request,
              (io.grpc.stub.StreamObserver<cdTP2.Event>) responseObserver);
          break;
        case METHODID_GET_SPEED_ID:
          serviceImpl.getSpeedID((cdTP2.Filter) request,
              (io.grpc.stub.StreamObserver<cdTP2.Events>) responseObserver);
          break;
        case METHODID_GET_SPEED_FROM_LOCAL:
          serviceImpl.getSpeedFromLocal((cdTP2.Filter) request,
              (io.grpc.stub.StreamObserver<cdTP2.Events>) responseObserver);
          break;
        case METHODID_GET_SPEED_FROM_SPEED:
          serviceImpl.getSpeedFromSpeed((cdTP2.Filter) request,
              (io.grpc.stub.StreamObserver<cdTP2.Events>) responseObserver);
          break;
        case METHODID_GET_CONSUMERS:
          serviceImpl.getConsumers((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<cdTP2.Consumers>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FrontEndToClientBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FrontEndToClientBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return cdTP2.Chat.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FrontEndToClient");
    }
  }

  private static final class FrontEndToClientFileDescriptorSupplier
      extends FrontEndToClientBaseDescriptorSupplier {
    FrontEndToClientFileDescriptorSupplier() {}
  }

  private static final class FrontEndToClientMethodDescriptorSupplier
      extends FrontEndToClientBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FrontEndToClientMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FrontEndToClientGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FrontEndToClientFileDescriptorSupplier())
              .addMethod(getGetHighestSpeedMethod())
              .addMethod(getGetSpeedFromDateMethod())
              .addMethod(getGetSpeedIDMethod())
              .addMethod(getGetSpeedFromLocalMethod())
              .addMethod(getGetSpeedFromSpeedMethod())
              .addMethod(getGetConsumersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
