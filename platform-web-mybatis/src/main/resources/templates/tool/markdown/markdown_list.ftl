<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        文章管理 <small>列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">系统工具</a></li>
        <li class="active">文章管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <!-- /.box-header -->
                <div class="dataTables_filter" id="searchDiv">
                    <input placeholder="请输入文章标题" name="title" class="form-control" type="search" likeOption="true" />
                    <input placeholder="请输入关键字" name="keywords" class="form-control" type="search" likeOption="true" />

                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" 	data-btn-type="search">查询</button>
                        <button type="button" class="btn btn-default" data-btn-type="reset">重置</button>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" data-btn-type="add"><i class="fa fa-pencil">&nbsp;新增文章</i></button>
                    </div>
                </div>
                <div class="box-body">
                    <table id="markdown_table" class="table table-border table-hover">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>

<script src="${request.contextPath}/static/common/js/dataTables.js"></script>
<script>
    //tableId,queryId,conditionContainer
    var markdownTable;
    var selectId="${selectId?default(0)}";

    $(function() {
        //表格中有按钮，不进行行选中
        var md_config={
            singleSelect:null
        };
        //init table and fill data
        markdownTable = new CommonTable("markdown_table", "markdown_list", "searchDiv",md_config);

        $('button[data-btn-type]').click(function() {
            //button event
            var action = $(this).attr('data-btn-type');
            switch (action) {
                case 'add':
                    window.loadPage(basePath+"/markdown/edit");
                    break;
            }

        });
        if(selectId!="0"){
            setTimeout(function(){markdownTable.selectRow(selectId);},100);

        }
        //form_init();
    })

    function fnRenderOperator(value,type,rowObj,oSetting){
        var oper="<a href='#' onclick='previewMD(\""+rowObj.id+"\")'><i class='fa'>浏览</i></a>";
        oper+="&nbsp;&nbsp;";
        oper+="<a href='#' onclick='editMD(\""+rowObj.id+"\")'><i class='fa'>编辑</i></a>";
        oper+="&nbsp;&nbsp;";
        oper+="<a href='#' onclick='deleteMD(\""+rowObj.id+"\")'><i class='fa'>删除</i></a>";
        return oper;
    }

    function editMD(id){
        window.loadPage(basePath+"/markdown/edit?id="+id);
    }


    function deleteMD(id){
        modals.confirm("是否要删除该行数据？",function(){
            ajaxPost(basePath+"/markdown/delete/"+id,null,function(data){
                if(data.success){
                    markdownTable.reloadRowData();
                }else{
                    modals.error("用户数据被引用，不可删除！");
                }
            });
        });
    }

    function previewMD(id){
        window.loadPage(basePath+"/markdown/preview?id="+id);
    }

</script>
