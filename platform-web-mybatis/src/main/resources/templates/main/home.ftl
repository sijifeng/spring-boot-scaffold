<style>
    .info-box {
        border: 1px solid rgba(0, 0, 0, 0.1);
    }

    .info-box-text {
        text-transform: none;
    }

    .info-box:hover .info-box-icon {
        font-size: 60px;
    }

    .info-box-text-sm {
        font-size: 12px;
        font-style: normal;
    }

    .info-box-text-italic {
        font-size: 14px;
        font-style: italic;
        font-weight: normal;
    }

    .text-blod {
        font-weight: bold;
    }

    .title-sm {
        font-weight: normal;
        margin-left: 10px;
    }
</style>
<section class="content-header">
    <h1>
        AdminEAP
        <small>功能介绍</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">AdminEAP 功能</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Info boxes -->
    <a name="top"></a>
    <div class="row">
        <div class="col-md-12">
            <div class="box box-primary">
                <!-- /.box-header -->
                <div class="box-body bg-info text-center">
                    <div class="row">
                        <div class="col-md-6 text-right">
                            <img src="static/common/images/admineap.png" width="80px" height="80px"
                                 alt="AdminEAP"/>
                        </div>
                        <div class="col-md-6 text-left" style="padding-left:0">
                            <h1 class="box-title text-blod">AdminEAP</h1>
                        </div>
                    </div>
                    <div class="row" style="padding:5px;">
                        <div class="col-md-12">
                            <label class="info-box-text-italic">An Enterprise Application Platform Based on AdminLTE,
                                Make
                                Development Easy</label>
                        </div>
                    </div>
                    <div class="row" style="padding-bottom:15px">
                        <div class="col-md-12">
                            基于AdminLTE的企业应用开发平台，封装和集成多种组件，提供前端、后端整体解决方案，使得WEB开发更简单
                        </div>
                    </div>
                    <div class="row" id="category">
                        <a class="btn btn-info" href="#component"><i class="fa fa-cog">&nbsp;Component 组件集成</i></a>
                        <a class="btn btn-danger" href="#curd"><i class="fa fa-list-alt">&nbsp;CURD 增删改查</i></a>
                        <a class="btn btn-success" href="#tool"><i class="fa fa-wrench">&nbsp;Tool 系统工具</i></a>
                        <a class="btn btn-warning" href="#workflow"><i class="fa fa-edit">&nbsp;Workflow 工作流</i></a>
                        <a class="btn btn-success" href="#security"><i class="fa fa-lock">&nbsp;System Security
                            权限与安全</i></a>
                        <a class="btn btn-danger" href="#github"><i class="fa fa-github">&nbsp;Github/License</i></a>
                        <a class="btn btn-info" href="#contact"><i class="fa fa-link">&nbsp;Contact us 联系我们</i></a>
                    </div>

                </div>

                <!-- /.row -->
            </div>
            <!-- ./box-body -->
        </div>
        <!-- /.box -->
    </div>
    <a name="component"></a>
    <div class="row">
        <div class="col-md-12">
            <div class="box box-info">
                <div class="box-header">
                    <h3 class="box-title">Component 组件集成</h3>
                    <label class="title-sm">封装和集成多个前端开源组件，让前端开发更简单</label>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool"
                                data-widget="collapse">
                            <i class="fa fa-minus"></i>
                        </button>
                        <a type="button" class="btn btn-box-tool" href="#top" title="回到顶部">
                            <i class="fa fa-arrow-up"></i>
                        </a>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/markdown/preview/modal">
					<span class="info-box-icon bg-aqua"><i
                            class="fa fa-windows"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">modals</span>
										<span class="info-box-text">
											模态窗体组件
										</span>
										<span class="info-box-text-sm">
											提醒、警告、成功、错误、确认、模态窗体的封装
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/user/page/edit">
					<span class="info-box-icon bg-aqua"><i
                            class="fa fa-file-text"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">BaseForm</span>
										<span class="info-box-text">
											表单组件
										</span>
										<span class="info-box-text-sm">
                                            表单控件初始化、数据收集、数据回填、数据校验
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->

                        <!-- /.col -->

                        <!-- fix for small devices only -->
                        <div class="clearfix visible-sm-block"></div>

                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/user/list">
					<span class="info-box-icon bg-aqua"><i
                            class="fa fa-list"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">DataTables</span>
										<span class="info-box-text">
											数据列表组件
										</span>
										<span class="info-box-text-sm">
                                            xml配置、支持分页、排序、查询条件、多表头、对齐方式配置
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/user/page/edit">
					<span class="info-box-icon bg-aqua"><i
                            class="fa fa-check-square"></i></span>

                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Bootstrap Validator</span>
										<span class="info-box-text">
											表单校验组件
										</span>
										<span class="info-box-text-sm">
											集成的Bootstarp Validator组件，支持对表单数据的多种校验要求
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->
                        <!-- /.progress-group -->
                    </div>
                    <div class="row">
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/dict/tree">
					<span class="info-box-icon bg-aqua"><i
                            class="fa fa-tree"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Bootstrap Treeview</span>
										<span class="info-box-text">
											树形组件
										</span>
										<span class="info-box-text-sm">
										集成Bootstrap Treeview组件，树形数据加载、显示、事件绑定
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/markdown/edit">
                                <span class="info-box-icon bg-aqua"><i class="fa fa-tags"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Bootstrap Tagsinput</span>
										<span class="info-box-text">
                                            标签组件
										</span>
										<span class="info-box-text-sm">
                                            集成Bootstrap Tagsinput组件，支持标签的添加、新增
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>

                        <!-- fix for small devices only -->
                        <div class="clearfix visible-sm-block"></div>

                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/user/page/edit">
					<span class="info-box-icon bg-aqua"><i
                            class="fa fa-user"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">fullAvatarEditor</span>
										<span class="info-box-text">
											头像上传组件
										</span>
										<span class="info-box-text-sm">
											集成fullAvatarEditor，基于flash插件的头像上传、截取
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/function/tree">
                                <span class="info-box-icon bg-aqua"><i class="fa fa-fonticons"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">IconSelector</span>
										<span class="info-box-text">
                                            图标选择器组件
										</span>
										<span class="info-box-text-sm">
                                            选择字体font awesome，glyphicons字体图标
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.col -->
                </div>

                <!-- /.row -->
            </div>
            <!-- ./box-body -->
        </div>
        <!-- /.box -->
    </div>
    <!-- /.col -->
    <a name="curd"></a>
    <div class="row">
        <div class="col-md-12">
            <div class="box box-danger">
                <div class="box-header">
                    <h3 class="box-title">CURD 增删改查</h3>
                    <label class="title-sm">多种形式的增删改查Demo，让基础功能开发更加高效、便捷</label>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool"  data-widget="collapse">
                            <i class="fa fa-minus"></i>
                        </button>
                        <a type="button" class="btn btn-box-tool" href="#top" title="回到顶部">
                            <i class="fa fa-arrow-up"></i>
                        </a>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/user/list">
                                <span class="info-box-icon bg-red"><i class="fa fa-table"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">CURD-Dialog</span>
										<span class="info-box-text">
											弹窗形式的CURD
										</span>
										<span class="info-box-text-sm">
                                            基于modals弹窗CURD，适用于属性少、需要定位行的需求
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/user/tab/list">
					<span class="info-box-icon bg-red"><i
                            class="fa fa-list-alt"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">CURD-Tab</span>
										<span class="info-box-text">
											Tab页形式的CURD
										</span>
										<span class="info-box-text-sm">
                                            基于Tab页的CURD，适用属性多、行定位的需求，页面不分离
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->

                        <!-- /.col -->

                        <!-- fix for small devices only -->
                        <div class="clearfix visible-sm-block"></div>

                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/user/page/list">
					<span class="info-box-icon bg-red"><i
                            class="fa fa-indent"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">CURD-Page</span>
										<span class="info-box-text">
											新页面形式的CURD
										</span>
										<span class="info-box-text-sm">
											在新的页面新增、编辑，适用属性多，但不能行定位
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/dict/tree">
					<span class="info-box-icon bg-red"><i
                            class="fa fa-align-right"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">CURD-Treeview</span>
										<span class="info-box-text">
											树形组件的CURD
										</span>
										<span class="info-box-text-sm">
											对树形组件节点的新增、编辑、删除，适用树形结构
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.col -->
                </div>

                <!-- /.row -->
            </div>
            <!-- ./box-body -->
        </div>
        <!-- /.box -->
    </div>
    <a name="tool"></a>
    <div class="row">
        <div class="col-md-12">
            <div class="box box-success">
                <div class="box-header">
                    <h3 class="box-title">Tool 系统工具</h3>
                    <label class="title-sm">使用在线工具，更快地辅助系统开发</label>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool"  data-widget="collapse">
                            <i class="fa fa-minus"></i>
                        </button>
                        <a type="button" class="btn btn-box-tool" href="#top" title="回到顶部">
                            <i class="fa fa-arrow-up"></i>
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/markdown/edit">
                                <span class="info-box-icon bg-green"><i class="fa fa-pencil"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Markdown Editor</span>
                                    <span class="info-box-text">
                                        markdown编辑器
                                    </span>
                                    <span class="info-box-text-sm">
                                        集成Editor.md组件，markdown语法的编辑、修改、预览
                                    </span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-copy"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Code Generator</span>
                                    <span class="info-box-text">
                                        代码生成器
                                    </span>
                                    <span class="info-box-text-sm">
                                        一键式生成CURD的大部分功能代码，简化开发步骤
                                    </span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>

                        <!-- fix for small devices only -->
                        <div class="clearfix visible-sm-block"></div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-clock-o"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Quartz Job</span>
                                    <span class="info-box-text">
                                        自动任务
                                    </span>
                                    <span class="info-box-text-sm">
                                        基于quarz的自动定时任务配置，支持集群，监控任务执行情况
                                    </span>
                                </div>
                            </div>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-align-right"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Message</span>
										<span class="info-box-text">
											系统消息
										</span>
										<span class="info-box-text-sm">
											系统消息、邮件的发送、即时获取、阅读
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.col -->
                </div>

                <!-- /.row -->
            </div>
            <!-- ./box-body -->
        </div>
        <!-- /.box -->
    </div>
    <a name="workflow"></a>
    <div class="row">
        <div class="col-md-12">
            <div class="box box-warning">
                <div class="box-header">
                    <h3 class="box-title">Workflow 工作流</h3>
                    <label class="title-sm">可视化工作流定义与流转，支撑审批业务流转</label>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool"  data-widget="collapse">
                            <i class="fa fa-minus"></i>
                        </button>
                        <a type="button" class="btn btn-box-tool" href="#top" title="回到顶部">
                            <i class="fa fa-arrow-up"></i>
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-edit"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Workflow Definition</span>
                                    <span class="info-box-text">
                                        工作流定义
                                    </span>
                                    <span class="info-box-text-sm">
                                        可视化工作流节点、节点转换、岗位的配置
                                    </span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-edit"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Workflow Process</span>
                                    <span class="info-box-text">
                                        工作流审批处理
                                    </span>
                                    <span class="info-box-text-sm">
                                        工作流审批、审批进度显示
                                    </span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>

                        <!-- fix for small devices only -->
                        <div class="clearfix visible-sm-block"></div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-edit"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Workflow Todo/Done</span>
                                    <span class="info-box-text">
                                        工作流待办已办
                                    </span>
                                    <span class="info-box-text-sm">
                                        工作流待办列表、已办列表查询与显示
                                    </span>
                                </div>
                            </div>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-edit"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Workflow API</span>
										<span class="info-box-text">
											工作流API
										</span>
										<span class="info-box-text-sm">
											工作流接口文档，定义了接口的参数和返回值，以及使用场景
										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.col -->
                </div>

                <!-- /.row -->
            </div>
            <!-- ./box-body -->
        </div>
        <!-- /.box -->
    </div>
    <a name="security"></a>
    <div class="row">
        <div class="col-md-12">
            <div class="box box-success">
                <div class="box-header">
                    <h3 class="box-title">System Security 系统安全</h3>
                    <label class="title-sm">基于Shiro，RBAC的权限管理，同时防范SQL注入、CSRF、XSS攻击</label>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool"  data-widget="collapse">
                            <i class="fa fa-minus"></i>
                        </button>
                        <a type="button" class="btn btn-box-tool" href="#top" title="回到顶部">
                            <i class="fa fa-arrow-up"></i>
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box" data-url="/role/list">
                                <span class="info-box-icon bg-green"><i class="fa fa-key"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">RBAC</span>
                                    <span class="info-box-text">
                                        基于角色的访问权限控制
                                    </span>
                                    <span class="info-box-text-sm">
                                        角色关联功能，用户绑定角色，用户授权
                                    </span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-lock"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Against CSRF</span>
                                    <span class="info-box-text">
                                        防范跨站请求伪造攻击
                                    </span>
                                    <span class="info-box-text-sm">
                                        使用Token+注解配置方式在拦截器中防范CSRF攻击
                                    </span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>

                        <!-- fix for small devices only -->
                        <div class="clearfix visible-sm-block"></div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-lock"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Against XXS</span>
                                    <span class="info-box-text">
                                        防范跨站脚本攻击
                                    </span>
                                    <span class="info-box-text-sm">
                                        使用Servlet过滤方式防范XXS攻击
                                    </span>
                                </div>
                            </div>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon"><i class="fa fa-lock"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text text-blod">Against SQL Injection</span>
										<span class="info-box-text">
											防范SQL注入攻击
										</span>
										<span class="info-box-text-sm">

										</span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.col -->
                </div>

                <!-- /.row -->
            </div>
            <!-- ./box-body -->
        </div>
        <!-- /.box -->
    </div>
    <a name="contact"></a>
    <a name="github"></a>
    <div class="row">
        <div class="col-md-12">
            <div class="box box-danger">
                <div class="box-header">
                    <h3 class="box-title">Github/Contact us</h3>
                    <label class="title-sm"></label>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool"  data-widget="collapse">
                            <i class="fa fa-minus"></i>
                        </button>
                        <a type="button" class="btn btn-box-tool" href="#top" title="回到顶部">
                            <i class="fa fa-arrow-up"></i>
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="info-box" data-flag="github" data-url="http://">
                                <span class="info-box-icon bg-red"><i class="fa fa-github"></i></span>
                                <div class="info-box-content">
                                    <span class="info-box-text-sm">遵循和使用MIT License 开源协议，无论个人还是公司，都可以免费自由使用。</span>
                                    <span  class="info-box-text">
                                      <iframe src="https://ghbtns.com/github-btn.html?user=bill1012&amp;repo=AdminEAP&amp;type=star&amp;count=true" scrolling="0" width="115px" height="20px" frameborder="0"></iframe>
                                        <!-- <iframe src="https://ghbtns.com/github-btn.html?user=bill1012&amp;repo=AdminEAP&amp;type=fork&amp;count=true" scrolling="0" width="138px" height="20px" frameborder="0"></iframe>
                                       <iframe src="https://ghbtns.com/github-btn.html?user=bill1012&amp;repo=AdminEAP&amp;type=watch&amp;count=true&amp;v=2" scrolling="0" width="120px" height="20px" frameborder="0"></iframe>-->
                                    </span>
                                    <span class="info-box-text">
                                        <a class="btn btn-danger" href="https://github.com/bill1012/AdminEAP"><i class="fa fa-lg fa-github"></i>&nbsp;Github地址</a>
                                        <a class="btn btn-danger" href="https://github.com/bill1012/AdminEAP/archive/master.zip"><i class="fa fa-lg fa-github"></i>&nbsp;Github下载源码</a>
                                        <a class="btn btn-danger" href="https://github.com/bill1012/AdminEAP/issues/new"><i class="fa fa-lg fa-github"></i>&nbsp;提交Bug或建议</a>
                                    </span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="info-box" data-flag="contact">
                                <span class="info-box-icon bg-aqua"><i class="fa fa-envelope"></i></span>
                                <div class="info-box-content">
                                    <span class="progress-description">
                                        <i class="fa fa-envelope"></i>&nbsp; ：admin@admineap.com / jrn1012@petrochina.com.cn
                                    </span>
                                     <span class="progress-description">
                                        <i class="fa fa-wechat"></i>&nbsp;：jrn1012
                                    </span>
                                    <span class="progress-description">
                                        <i class="fa fa-qq"></i>&nbsp; ：475572229
                                    </span>
                                    <span class="progress-description">
                                        <i class="fa fa-lg fa-github"></i>&nbsp：<a href="https://github.com/bill1012/AdminEAP">https://github.com/bill1012/AdminEAP</a>
                                    </span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <button class="btn btn-primary" id="btn_msg" style="width: 100%"><i class="fa fa-commenting"></i>&nbsp;&nbsp;我是billJiang，如果有任何意见/建议/创意/问题/想法，点击该按钮给我留言</button>
        </div>
    </div>
</section>
<!-- /.content -->
<!-- /.content-wrapper -->

<script type="text/javascript">
    $(function () {
        $(".info-box").each(function (index, item) {
            $(item).hover(function () {
                //$(this).css({"background":"red"});
                var bg = $(this).find("span:eq(0)").attr("class").replace("info-box-icon", "").trim();
                if(bg) {
                    $(this).find("span:eq(0)").removeClass(bg);
                    $(this).addClass(bg);
                }else{
                    bg = $(this).attr("class").replace("info-box", "").trim();
                    $(this).find("span:eq(0)").addClass(bg);
                    $(this).removeClass(bg);
                }
            }, function () {
                var bg = $(this).attr("class").replace("info-box", "").trim();
                if(bg) {
                    $(this).find("span:eq(0)").addClass(bg);
                    $(this).removeClass(bg);
                }else {
                    bg = $(this).find("span:eq(0)").attr("class").replace("info-box-icon", "").trim();
                    $(this).find("span:eq(0)").removeClass(bg);
                    $(this).addClass(bg);
                }
            });
        });



        $(".info-box").each(function (index, item) {
            $(item).click(function () {
                if($(this).data("flag")){
                    return;
                }
                if ($(this).data("url")) {
                    location.hash = "#main";
                    loadPage(basePath + $(this).data("url"));
                }
                else
                    modals.info("该功能demo尚未完成，敬请期待！");
            })
        });

        $("#btn_msg").click(function(){
            modals.openWin({
                winId:"messageWin",
                title:'给【billJiang】留言',
                width:'750px',
                url:basePath+"/message/mail/edit"
            });
        })


    });
</script>