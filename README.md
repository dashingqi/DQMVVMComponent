#### DQMVVMComponent

######  MVVM+组件化的架构形式（2020/04/25）

#### 更新日志
- 抽取BaseMvvMActivity和BaseMvvMFragment（2020/04/25）
- 完善BaseLazyFragment;同时加入网络请求组件，目前要自定义请求回调，做业务层的分发。（2020/04/26）
- 配置路由框架ARouter，为功能组件提供对外调用的接口（library-service）(2020/04/29)
- 完善BaseCallBack，针对不同情况的业务分发（2020/05/2）
- 完善LiveDateCallBack，新增SmartRefreshLayout，DQStateLayout（2020/5/15）
- 为状态页面和下拉加载页面添加绑定状态
- 需要测试Logger打印，为base中的重要逻辑添加日志
- 优化Fragment的懒加载代码，采用代理模式去控制业务层的分发
  - 对于Fragment的懒加载，主要把握住如下几个点
    - 当前的view不能为null
    - 当前的fragment是对用户可见
    - 当前fragment 没有加载过数据
    - 当前fragment的是处于前台的（onStart()）
    - 当前的fragment不是隐藏的
  - 为什么使用代理模式，来控制懒加载
    - 减轻Fragment中的代码量，提供更好的扩展性（我觉得大多数的设计都是为了减少代码量和灵活性而设计的）
    - 所谓代理，就好比我们打官司我们都会请律师，律师来帮助我们处理这个过程的一些细节，我们只要花钱等结果就行。（打官司都是为了一个结果，我想原告不会享受这个过程的吧）
    - 正常的代理模式都是 代理类和被代理类需要实现相同的功能接口，代理类持有被代理类的引用，代理类内部调用被代理类的功能方法前后可以有自己的处理逻辑
    - 在这里，功能接口中，我们提供了实现功能的抽象方法，和执行功能状态的抽象方法。
      - 比如我们提供加载View的抽象方法，提供不执行懒加载策略的抽象方法，这些方法都是在Fragment中有具体的实现
      - 可能在某天，我们接到某个特殊需求，这堆Fragment中一些需要懒加载，一些不需要，这时候我们可以把功能接口中添加一个对外实现的方法，在Fragment实现这个方法并提供这个状态，我们的代理类中根据这个状态做业务逻辑分发的处理
- 为App内的文章列表添加点击事件，展示文章的详情(2020/6/13)
- 调整状态栏，首页改版 （2020/6/14）
- 完成通用搜索控件，完成首页搜索（2020/6/18）
- 完成文章收藏的接口接入，以为未登录状态时的处理（2020/7/13）
- 完成ARouter系列的源码分析（2020/7/22）
- 完成分享功能组件（2020/7/25）

#### 组件化部分划分-wanAndroid项目（2020/04/28）
##### 功能模块组件划分
- 首页（module-home）
- 个人（module-user）
- 公众号文章(module-article)
- 项目（module-project）
- 入口（module-entrance）
- 广场 (module-square)
- 收藏 (module-collect)
##### 公共组件
- library-base(抽取的Base)
- library-network(网络层)
- library-resource（公用的资源文件）
- library-service(组件间存在调用关系，为其提供调用接口)
##### 其他第三方公用组件(功能组件)
###### module-location(定位)
###### module-share（分享）
- 介绍
> 是基于友盟分享SDK基础上，抽取的一个功能分享组件，同样在Service层提供了调用入口，通过ARouter的IoC的ByType特性（定义一个接口 继承 IProvider，在组件内部实现这个接口以及定义的抽象方法）对外提供调用
> 使用例子 ARouter.getInstance().navigation(ShareService::class.java).performWxShare(WxShareBean,Activity)
> WxShareBean 用于传输需要分享的数据，内部工具类通过解析WxShareBean携带的数据，来进行分享的调用
- 遇到的问题
> 在定义WXEntryActivity这个类的时候，一定要注意所在的包名，因为我们申请微信分享的Id时候填写的包名是应用的applicationId，所以在组件中应该新建一个 applicationIdName.wxapi的包名，在该包名下建WXEntryActivity
###### module-statistics（数据埋点统计）
###### module-crash（异常上报）
##### 一些理解（2020/04/30）
- 组件：指的是一个单一的功能组件，比如分享组件，支付组件
- 模块：指的是独立的业务模块，比如购物车模块，首页模块，分类模块等。相比较于组件，模块的粒度更大。模块可能包含不同的组件。
- 组件是功能的导向，而模块是业务的导向的。
#### StateLayout
- 加载的状态
- 错误的状态
- 无数据的状态
- 成功的状态
- 需要提供对外设置这几种状态的方法（加载，错误，无数据，成功）
- 自定义属性
  - 加载状态的布局
  - 错误状态的布局
  - 无数据状态的布局
#### HistoryLayout(搜索的通用布局)
- 布局
    - 标题（最近搜索）
    - 删除按钮
    - 流式布局（TagFlowLayout）
        - 流式布局的适配器，指定布局样式，设置数据
        - 拿到流式布局，设置item的点击事件
            - 点击事件主要就是发出请求（内部的search方法）
- 继承ConstraintLayout
    - init 中添加布局，为删除按钮添加点击事件
        - 点击事件的目的
            - 流式布局的适配器数据源清空，刷新适配器
            - 清空本地存储的数据
    - 提供隐藏和显示布局的方法
        - VISIBLE 和 GONE
    - 内部的search方法
        - 获取到EditText中的文本
        - 通过回调，指定数据的请求
    - 对外提供的方法，用于绑定输入框
        - EditText
            - 当获取到焦点的时候，需要把布局展示处理
            - 当失去焦点的时候，需要吧布局隐藏处理
            - 监听键盘中的搜索按钮
        - 本地存储的标识
        - 回调的接口
- LiveData与DataBinding进行绑定
    - LiveData用于从EditText中获取输入的数据 android:text="@={viewModel.liveData}"
    - 本地的请求通过LiveData拿到输入框中数据
    - 注意使用LiveData需要绑定Activity生命周期 setLifeCycleOwner(this)
#### ARouter源码系列教程
- [Android开发之APT技术](https://www.jianshu.com/p/5ee5b16ed5d8)
- [ARouter注解处理器分析-RouteProcessor](https://www.jianshu.com/p/746b9bed4884)
- [ARouter注解处理器分析-InterceptorProcessor](https://www.jianshu.com/p/a9c1a1cef690)
- [ARouter注解处理器分析-AutowiredProcessor](https://www.jianshu.com/p/6408428b11ae)
- [ARouter源码分析-初始化](https://www.jianshu.com/p/18a89ba11882)
- [ARouter源码分析-页面跳转](https://www.jianshu.com/p/48dbaea12813)
- [ARouter源码分析-拦截器](https://www.jianshu.com/p/25157c8119b0)
- [ARouter源码分析-降级策略](https://www.jianshu.com/p/8ada64425e1d)
- [ARouter源码分析-依赖注入](https://www.jianshu.com/p/de2d4f386e9a)

#### Lifecycle使用与分析
- [Lifecycle使用篇](https://www.jianshu.com/p/61a13156c19c)
- [Lifecycle原理篇](https://www.jianshu.com/p/06c8ba0a1263)

#### ViewModel分析
- [ViewModel原理篇](https://www.jianshu.com/p/e2cc680d5829)

#### LiveData使用与分析
- [LiveData使用篇](https://www.jianshu.com/p/0b89514d5a4c)
- [LiveData原理篇](https://www.jianshu.com/p/d8c0825c339d)

#### RecyclerView系列
- [RecyclerView基础篇-列表数据展示](https://www.jianshu.com/p/bb8c76cec3cd)
- [RecyclerView基础篇-ItemDecoration](https://www.jianshu.com/p/2c7e0011e5c1)
- [RecyclerView基础篇-Item添加动画](https://www.jianshu.com/p/4c1eb409bc0e)
- [RecyclerView基础篇-Api解释](https://www.jianshu.com/p/7bb51be254d4)

#### 痛点
##### LiveData
###### 痛点一
- 当横竖屏切换的时候伴随着页面的销毁重建，会导致页面每次屏幕旋转都会重新执行observe；
- LiveData保证订阅者总能在值变化的时候观察到最新的值，并且每个初次订阅的观察者都会执行一次回调方法；
- 在一个UI刷新周期内，LiveData连续两次post值，回调处只能收到最新一次的值，之前的都会被丢弃；


