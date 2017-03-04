<link rel="stylesheet" href="${request.contextPath}/static/common/libs/markdown/editor.md/css/editormd.min.css">
<link rel="stylesheet" href="${request.contextPath}/static/common/libs/tagsinput/bootstrap-tagsinput.css">
<link rel="stylesheet" href="${request.contextPath}/static/common/libs/tagsinput/app.css">

<section class="content-header">
    <h1>
        <span>MarkDown编辑</span>
        <small id="title_sm">新增</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">Markdown编辑器</li>
    </ol>
</section>

<section class="content">
    <div class="row form-horizontal">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header bg-info" style="padding-bottom: 0">
                    <div class="row" style="padding-bottom:5px;">
                        <div class="col-sm-7">
                            <div clas="form-group">
                                <input class="form-control" type="text" id="title" placeholder="请输入文章标题……">
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <div clas="form-group">
                                <input  type="text" id="keywords" data-role="tagsinput" placeholder="关键字" >
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-sm-7">
                            <div class="col-sm-4">
                                <select id="editormd-theme-select" class="form-control">
                                    <option selected="selected" value="">工具栏样式</option>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="editor-area-theme-select" class="form-control">
                                    <option selected="selected" value="">编辑区样式</option>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="preview-area-theme-select" class="form-control">
                                    <option selected="selected" value="">预览区样式</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-5 pull-right">
                            <div class="col-sm-6">
                                <label class="control-label">
                                    <input type="checkbox" name="autoHeight" data-flag="icheck" class="flat-red"
                                           id="autoHeight"> 自适应高度
                                </label>
                                <input type="hidden" name="code" id="code">
                            </div>
                            <div class="col-sm-6 text-right">
                                <div class="btn-group ">
                                    <button class="btn btn-default"  id="backMD"><i class="fa fa-reply">&nbsp;&nbsp;返回</i></button>
                                    <button class="btn btn-primary"  id="submitMD"><i class="fa fa-save">&nbsp;&nbsp;保存</i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div clas="box-body">
                    <div id="editormd">
                        <textarea style="display:none;"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
<script type="text/javascript" src="${request.contextPath}/static/common/libs/markdown/editor.md/js/editormd.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/common/libs/tagsinput/bootstrap-tagsinput.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/plugins/iCheck/icheck.min.js"></script>
<script>
    var editor;

    function themeSelect(id, themes, lsKey, callback) {
        var select = $("#" + id);

        for (var i = 0, len = themes.length; i < len; i++) {
            var theme = themes[i];
            var selected = (localStorage[lsKey] == theme) ? " selected=\"selected\"" : "";

            select.append("<option value=\"" + theme + "\"" + selected + ">" + theme + "</option>");
        }

        select.bind("change", function () {
            var theme = $(this).val();

            if (theme === "") {
                return false;
            }
            localStorage[lsKey] = theme;
            callback(select, theme);
        });

        return select;
    }

    $(function () {
        //---初始化控件-----
        //高度自定义框
        $("#autoHeight").iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });

        var isAutoHeight = localStorage.isAutoHeight ? localStorage.isAutoHeight : false;
        if (isAutoHeight == "false" || !isAutoHeight) {
            $("#autoHeight").iCheck("uncheck");
        } else {
            $("#autoHeight").iCheck("check");
        }
        //高度不自定义时高度
        var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;

        $("#autoHeight").on("ifChanged", function (event) {
            isAutoHeight = $("#autoHeight").prop("checked");
            localStorage.isAutoHeight = isAutoHeight;
            if(editor){
                editor.config("autoHeight",isAutoHeight);
                //alert(isAutoHeight+" --> "+clientHeight);
                if(!isAutoHeight)
                    editor.config("height",clientHeight);
            }
        });

        //-----数据回填-------------
        var mid="${id?default('')}";
        if(mid!=''){
            $("#title_sm").text("编辑");
            ajaxPost(basePath+"/markdown/get/"+mid,null,function(result){
                if(localStorage.mdtitle&&localStorage.mdtitle!=result.title){
                    modals.confirm("浏览器缓存的文章与当前要编辑的文章不一致，是否替换缓存中内容？",function(){
                        fillLocalStorage(result);
                        $("#title").val(result.title);
                        $("#keywords").tagsinput("add",result.keywords);
                        editor.setMarkdown(result.content);
                    });
                }else{
                    fillLocalStorage(result);
                }
                $("#code").val(result.code);
            })
        }

        function fillLocalStorage(result){
            localStorage.mdtitle=result.title;
            localStorage.mdkeywords=result.keywords;
            localStorage.markdownContent=result.content;
        }
        //文章标题
        if(localStorage.mdtitle){
            $("#title").val(localStorage.mdtitle);
        }
        $("#title").on("change",function(event){
            var $title=$(event.target);
            localStorage.mdtitle=$title.val();
        })

        //keywords 关键字
        $("#keywords").on("change",function(event){
            var $element=$(event.target);
            var val = $element.val();
            //修复placeholder问题
            if(val){
                $element.prev(".bootstrap-tagsinput").find("input:eq(0)").attr("placeholder",null);
            }else{
                $element.prev(".bootstrap-tagsinput").find("input:eq(0)").attr("placeholder","关键字");
            }
            localStorage.mdkeywords=val;
        });
        if(localStorage.mdkeywords){
            //alert(localStorage.mdkeywords);
            $("#keywords").tagsinput("add",localStorage.mdkeywords);
        }

        //markdown 默认内容
        var markdownContent = null;

        $.ajax({
            type: "get",
            url: basePath + "/static/common/libs/markdown/readme.md",
            async: false,
            success: function (md) {
                markdownContent = localStorage.markdownContent ? localStorage.markdownContent : md;
            }
        });

        editor = editormd("editormd", {
            width: "100%",
            height: clientHeight,
            theme: (localStorage.theme) ? localStorage.theme : "default",
            previewTheme: (localStorage.previewTheme) ? localStorage.previewTheme : "default",
            editorTheme: (localStorage.editorTheme) ? localStorage.editorTheme : "default",
            path: basePath + '/static/common/libs/markdown/editor.md/lib/',
            autoHeight: isAutoHeight == "true" ? true : false,

            codeFold : true,
            searchReplace : true,
            saveHTMLToTextarea : true,

            htmlDecode: "style,script,iframe",
            tex: true,
            emoji: true,
            taskList: true,
            flowChart: true,
            sequenceDiagram: true,
            markdown: markdownContent,
            syncScrolling:true,

            //图片上传
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "${request.contextPath}/file/markdownUpload",
            onchange: function () {
                localStorage.markdownContent = editor.getMarkdown();
            }
        });


        themeSelect("editormd-theme-select", editormd.themes, "theme", function ($this, theme) {
            editor.setTheme(theme);
        });

        themeSelect("editor-area-theme-select", editormd.editorThemes, "editorTheme", function ($this, theme) {
            editor.setCodeMirrorTheme(theme);
        });

        themeSelect("preview-area-theme-select", editormd.previewThemes, "previewTheme", function ($this, theme) {
            editor.setPreviewTheme(theme);
        });

        //导出pdf
        $("#submitMD").click(function(){
            var obj_md={};
            obj_md["content"]=editor.getMarkdown();
            obj_md["keywords"]=$("#keywords").val();
            obj_md["title"]=$("#title").val();
            obj_md["id"]="${id?default('')}";
            ajaxPost(basePath+"/markdown/save",obj_md,function(result){
                if(result.success){
                    modals.info("保存成功");
                    returnToList();
                }
            })
        });

        $("#backMD").click(function () {
            returnToList();
        });
    });

    function returnToList(){
        loadPage(basePath+"/markdown/list");
    }
</script>
