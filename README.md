#### DQMVVMComponent

######  MVVM+组件化的架构形式（2020/04/25）

#### 更新日志
- 抽取BaseMvvMActivity和BaseMvvMFragment（2020/04/25）
- 完善BaseLazyFragment;同时加入网络请求组件，目前要自定义请求回调，做业务层的分发。（2020/04/26）
- 配置路由框架ARouter，为功能组件提供对外调用的接口（library-service）(2020/04/29)

#### 组件化部分划分-wanAndroid项目（2020/04/28）
##### 功能模块组件划分
- 首页（module-home）
- 个人（module-user）
- 公众号文章(module-article)
- 项目（module-project）
- 入口（module-entrance）
##### 公共组件
- library-base(抽取的Base)
- library-network(网络层)
- library-resource（公用的资源文件）
- library-service(组件间存在调用关系，为其提供调用接口)
##### 其他第三方公用组件
- module-location(定位)
- module-share（分享）
- module-statistics（数据埋点统计）
- module-crash（异常上报）
##### 一些理解（2020/04/30）
- 组件：指的是一个单一的功能组件，比如分享组件，支付组件
- 模块：指的是独立的业务模块，比如购物车模块，首页模块，分类模块等。相比较于组件，模块的粒度更大。模块可能包含不同的组件。
- 组件是功能的导向，而模块是业务的导向的。