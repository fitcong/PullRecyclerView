## 关于

* 本控件参考了其他文章的内容,如有侵权请及时联系
>jack120612@163.com (请写上相关内容)

## 相关说明

### 1. 类说明
* ArrowRefreshHeader - 下拉刷新的头部View,没有进行高度的封装,需要自己实现各种状态对应的控件状态
* LoadingMoreFooter - 加载更多时展示的VIew,同样没有进行高度封装
* PullToRefreshHeaderHelper - 下拉刷新的帮助类
* PullToRefreshRecyclerView - 二次封装的RecyclerView,主要核心就是创建不同的ItemType来达成不同的item
* SimpleItemDecoration - 简易的分割线

### 2. 使用方法
* 关于基础使用方法我就不做赘述了,因为和正常的RecyclerView一样
* 添加头部,可以直接调用多次,添加多个
 ```java
 recyclerview.addHeader(header);
 ```
* 设置footer
 ```java
 recyclerview.addFooter(footer);
 ```
* 设置LoadMore,剔除耦合代码,可自定义,继承ILoadMoreState
```java
recyclerview.setLoadMoreView(loadView);
```
* 设置监听,直接包含的刷新和加载更多的方式
```java
recyclerview.setLoadingListener(new LoadingListener(){

    @Override
    public void onRefresh(){
        //...
        recyclerview.refreshComplete();
    }

    @Override
    public void onLoadMore(){
        //...
        recyclerview.loadMoreComplete();
        //在没有更多的时候可以直接设置
        recyclerview.setNoMore(true);//设置了这个就可以不用调用上面的方法
    }
});
```
* 设置相应的模式,模式有4种
```java
    // MODE_NONE   不需要下拉和加载
    // MODE_REFRESH_BOTTOM 只进行加载更多的操作
    // MODE_REFRESH_TOP 只进行刷新的操作
    // MODE_REFRESH_ALL 全部都进行(默认选项)
    recyclerview.setMode(PullToRefreshView.FreshMode.MODE_REFRESH_ALL);
```
## 附言
* 因为没有自己做很好看的下拉刷新View,所以就随便简单写了一下,需要的可以进行自定义.我这边只封装的了状态.

有问题欢迎提出或者直接邮件


 
