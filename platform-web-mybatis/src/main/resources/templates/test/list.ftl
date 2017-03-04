<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>调度管理<small>任务调度中心</small></h1>
    <!--
    <ol class="breadcrumb">
        <li><a><i class="fa fa-dashboard"></i>调度管理</a></li>
        <li class="active">调度中心</li>
    </ol>
    -->
</section>

<!-- Main content -->
<section class="content">

    <div class="row">
        <div class="col-xs-4">
            <div class="input-group">
                <span class="input-group-addon">执行器</span>
                <select class="form-control" id="jobGroup" >

                </select>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="input-group">
                <span class="input-group-addon">JobHandler</span>
                <input type="text" class="form-control" id="executorHandler" value="" autocomplete="on" >
            </div>
        </div>
        <div class="col-xs-2">
            <button class="btn btn-block btn-info" id="searchBtn">搜索</button>
        </div>
        <div class="col-xs-2">
            <button class="btn btn-block btn-success add" type="button">+新增任务</button>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">调度列表</h3>
                </div>
                <div class="box-body">
                    <table id="job_list" class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th name="id" >id</th>
                            <th name="username" >username</th>
                            <th name="name" >name</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>


<script type="text/javascript">

$(function() {
		// init date tables
	var jobTable = $("#job_list").dataTable({
		"deferRender": true,
		"processing" : true,
	    "serverSide": true,
		"ajax": {
			url: base_url + "/sysuser/pageList",
			type:"post",
	        data : function ( d ) {
	        	var obj = {};
	        	obj.jobGroup = 1;
	        	obj.executorHandler = $('#executorHandler').val();
	        	obj.start = d.start;
	        	obj.length = d.length;
                return obj;
            }
	    },
	    "searching": false,
	    "ordering": false,
	    //"scrollX": true,	// X轴滚动条，取消自适应
	    "columns": [
	                { "data": 'id', "bSortable": false, "visible" : false},
					{ "data": 'username', "visible" : true},
	                { "data": 'name', "visible" : true},
	                { "data": '操作' ,
	                	"render": function ( data, type, row ) {
	                		return function(){
	                			// status
	                			var pause_resume = "";
	                			if ('NORMAL' == row.jobStatus) {
	                				pause_resume = '<button class="btn btn-primary btn-xs job_operate" type="job_pause" type="button">暂停</button>  ';
								} else if ('PAUSED' == row.jobStatus){
									pause_resume = '<button class="btn btn-primary btn-xs job_operate" type="job_resume" type="button">恢复</button>  ';
								}
	                			// log url
	                			var logUrl = base_url +'/joblog?jobGroup='+ row.jobGroup +'&jobName='+ row.jobName;

	                			// log url
	                			var codeBtn = "";
	                			if(row.glueSwitch > 0){
									var codeUrl = base_url +'/jobcode?jobGroup='+ row.jobGroup +'&jobName='+ row.jobName;
									codeBtn = '<button class="btn btn-warning btn-xs" type="button" onclick="javascript:window.open(\'' + codeUrl + '\')" >GLUE</button>  '
								}

								// html
								var html = '<p id="'+ row.id +'" '+
									' jobGroup="'+ row.jobGroup +'" '+
									' jobName="'+ row.jobName +'" '+
									' jobCron="'+ row.jobCron +'" '+
									' jobDesc="'+ row.jobDesc +'" '+
									' author="'+ row.author +'" '+
									' alarmEmail="'+ row.alarmEmail +'" '+
									' executorHandler="'+row.executorHandler +'" '+
									' executorParam="'+ row.executorParam +'" '+
									' glueSwitch="'+ row.glueSwitch +'" '+
                                    ' childJobKey="'+ row.childJobKey +'" '+
									'>'+
									'<button class="btn btn-primary btn-xs job_operate" type="job_trigger" type="button">执行</button>  '+
									pause_resume +
									'<button class="btn btn-primary btn-xs" type="job_del" type="button" onclick="javascript:window.open(\'' + logUrl + '\')" >日志</button><br>  '+
									'<button class="btn btn-warning btn-xs update" type="button">编辑</button>  '+
									codeBtn +
									'<button class="btn btn-danger btn-xs job_operate" type="job_del" type="button">删除</button>  '+
									'</p>';

	                			return html;
							};
	                	}
	                }
	            ],
		"language" : {
			"sProcessing" : "处理中...",
			"sLengthMenu" : "每页 _MENU_ 条记录",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
			"sInfoEmpty" : "无记录",
			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix" : "",
			"sSearch" : "搜索:",
			"sUrl" : "",
			"sEmptyTable" : "表中数据为空",
			"sLoadingRecords" : "载入中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上页",
				"sNext" : "下页",
				"sLast" : "末页"
			},
			"oAria" : {
				"sSortAscending" : ": 以升序排列此列",
				"sSortDescending" : ": 以降序排列此列"
			}
		}
	});
});
</script>
