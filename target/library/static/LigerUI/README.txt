LigerUI是基于jQuery的UI框架，包括表单、布局、表格等等常用UI控件，以扩展性、使用简单、丰富UI的设计原则进行开发。

使用LigerUI可以快速轻松地创建风格统一的界面效果。



链接地址：

API: http://api.ligerui.com/
演示地址:http://demo.ligerui.com/
官方论坛: http://bbs.ligerui.com/
源码下载: 
http://git.oschina.net/ligerui/LigerUI/  			  (源码托管)
技术支持：http://www.cnblogs.com/leoxie2011/



QQ群交流群：

交流QQ群1群: 71664111
交流QQ群2群：104842296
交流QQ群3群：153770480
交流QQ群4群：190391819
交流QQ群5群：37205343
交流QQ群6群：237634156


V1.2.2更新记录
Tree
[需求]优化了加载数据的能力
[需求]增加isExpand(支持灵活地控制展开/收缩状态)
[需求]增加delay(支持延迟加载数据)
表格
[需求]对表格分页条数增加缓存处理
[需求]增加参数toolbarShowInLeft(控制工具条显示在左边还是右边)
表单
[需求]验证部分增加对旧版本的兼容处理
ListBox
[需求]增加方法getDataByValue
Tab
[需求]增加事件onClose、onCloseOther、onCloseAll、onReload

V1.2.1更新记录
核心
[需求]表单、表格编辑器的统一化处理
[BUG]支持jQuery高版本
[BUG]改善layout、dialog在ie下的拖拽操作体验
表单
[需求]表单内置验证支持
[需求]相关表单元素增加参数valueFieldCssClass(隐藏域css样式)
[需求]相关表单元素参数parms支持运行时动态获取
[需求]下拉框组件增加参数alwayShowInTop，增加方法reload、getSelected
[需求]listBox组件增加selectAll方法
[需求]popupEdit增加parms参数
[需求]文本框数值类型默认右对齐
[需求] 表单增加getData setData方法
表格
[需求]优化表格工具条的显示位置,并增加工具条标题的支持
[需求]表格树支持线性数据结构
[需求]表格 列支持auto
[BUG]修复grid不能确定取得更新数据的错误
[BUG]修复grid编辑器位置在显示标题出现错位的情况
[BUG]修改grid排序事件没有起效的问题
树
[需求]tree增加ajaxType、render(自定义html)、selectable(可选择判断函数)参数
V1.2.0更新记录
核心
[需求]支持解析html(属性、事件直接在html元素中定义即可)
表单
[需求]新增RadioList组件
[需求]新增CheckBoxList组件
[需求]新增ListBox组件
[需求]新增PopupEdit(弹出编辑)组件
[需求]表单组件加上readonly支持
[需求]TextBox增加属性currency(货币格式)
[需求]ligerForm组件增加参数labelCss、fieldCss、spaceCss、readonly
[需求]ligerForm组件增加事件onAfterSetFields
[需求]ligerForm组件增加buttons属性
[优化]ligerForm组件优化编辑构造器,增加popup、checkboxlist、radiolist、listbox等
[需求]DateEditor增加属性cancelable、readonly
[需求]DateEditor增加下拉图标样式
[优化]DateEditor和ComboBox下拉框位置优化
[需求]ComboBox增加属性condition(条件设置,配合grid使用)
[需求]ComboBox增加属性cancelable、css、renderItem(下拉选项自定义html函数)
[优化]ComboBox优化url模式，增加属性parms、ajaxType，方法setUrl、setParm
[需求]ComboBox支持自动完成(url模式和grid模式)，增加autocomplete
[优化]ComboBox增加方法getGridHeight、getText、setText
[需求]Button增加属性icon(图标)
树
[需求]增加参数needCancel
工具条
[需求]增加方法removeItem、setEnabled、setDisabled、isEnable
Tab
[需求]增加方法setHeader(设置页签项标题)
表格
[需求]Column Name支持点号访问子属性
[需求]增加属性isSelected(选择初始化函数)
[优化]优化编辑构造器,新增getText和setText
[需求]增加方法setParm、removeParm(ajax参数设置)、getChanges(获取修改过的数据)
[需求]增加事件onGroupExtend(分组展开事件)、onGroupCollapse(分组收缩事件)、onLoadData(加载数据前事件)
[BUG]解决编辑表格在 日期选择框框，或者下拉框 点击会结束编辑状态的情况
[BUG]解决grid刷新表格以后编辑状态还存在的问题
[BUG]解决固定列模式下无法自动根据表格内容调整高度的问题
[需求]增加loadData的别名方法reload
[需求]表格主体的横向滚动条默认不显示
[需求]column.editor的onChange、onChanged事件参数改变
弹窗
[需求]增加事件onContentHeightChange、onClose、onClosed、onStopResize
[BUG]限制弹窗不能拖拽到窗口边界外