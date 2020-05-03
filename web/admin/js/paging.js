(function($, window, document, undefined) {
	//定义分页类
	function Paging(element, options) {
		this.element = element;
		//传入形参
		this.options = {
			pageNo: options.pageNo||1,    //选中哪一页
			totalPage: options.totalPage,   //总共有多少页
			totalSize:options.totalSize,      //总共有多少条
			callback:options.callback
		};
		//根据形参初始化分页html和css代码
		this.init();
	}
	//对Paging的实例对象添加公共的属性和方法
	Paging.prototype = {
		constructor: Paging,
		init: function() {
			this.creatHtml();
			this.bindEvent();
		},
		creatHtml: function() {
			var me = this;
			var content = "";
			var current = me.options.pageNo;
			var total = me.options.totalPage;
			var totalNum = me.options.totalSize;
			content += "<a id=\"firstPage\">首页</a><a id='prePage' >上一页</a>";
			//总页数大于6时候
			if(total > 6) {
				//当前页数小于5时显示省略号
				if(current < 5) {
					for(var i = 1; i < 6; i++) {
						if(current == i) {
							content += "<a class='current'>" + i + "</a>";

						} else {
							content += "<a>" + i + "</a>";
						}
					}
					content += ". . .";
					content += "<a>"+total+"</a>";
				} else {
					 //判断页码在末尾的时候
					if(current < total - 3) {
						//5,   3   8   // 1 2 3 4 5 6 7 8 ... 10
						//6
						for(var k = (current - 2); k < (total - 3); k++) {
                            //console.log("current:" + (current + 3));
                            //console.log("k < current + 3:" + (k < current + 3));
                            if(current == k) {
								content += "<a class='current'>" + k + "</a>";
							} else {
								content += "<a>" + k + "</a>";
							}
						}
						content += ". . .";
						content += "<a>"+total+"</a>";
					//页码在中间部分时候	
					} else {
						content += "<a>1</a>";
						content += ". . .";
						for(var i = total - 4; i < total + 1; i++) {
							if(current == i) {
								content += "<a class='current'>" + i + "</a>";
							} else {
								content += "<a>" + i + "</a>";
							}
						}
					}
				}
				//页面总数小于6的时候
			} else {
				for(var i = 1; i < total + 1; i++) {
					if(current == i) {
						content += "<a class='current'>" + i + "</a>";
					} else {
						content += "<a>" + i + "</a>";
					}
				}
			}
			content += "<a id='nextPage'>下一页</a>";
			content += "<a id=\"lastPage\">尾页</a>";
			content += "<span class='totalPages'> 共<span>"+total+"</span>页 </span>";
			content += "<span class='totalSize'> 共<span>"+totalNum+"</span>条记录 </span>";
			me.element.html(content);

			if(this.options.pageNo == 1){
				$("#firstPage,#prePage").hide();
			}else {
                $("#firstPage,#prePage").show();
			}

            if(this.options.pageNo == this.options.totalPage){
                $("#nextPage,#lastPage").hide();
            }else {
                $("#lastPage,#lastPage").show();
            }
		},
		//添加页面操作事件
		bindEvent: function() {
			var me = this;
			me.element.off('click', 'a');
			me.element.on('click', 'a', function() {
				var num = $(this).html();
				var id=$(this).attr("id");
				if(id == "prePage") {
					if(me.options.pageNo == 1) {
						me.options.pageNo = 1;
					} else {
						me.options.pageNo = +me.options.pageNo - 1;

					}
				} else if(id == "nextPage") {
					if(me.options.pageNo == me.options.totalPage) {
						me.options.pageNo = me.options.totalPage
					} else {
						me.options.pageNo = +me.options.pageNo + 1;
					}

				} else if(id =="firstPage") {
					me.options.pageNo = 1;
				} else if(id =="lastPage") {
					me.options.pageNo = me.options.totalPage;
				}else{
					me.options.pageNo = +num;
				}
				me.creatHtml();
				if(me.options.callback) {
					me.options.callback(me.options.pageNo);
				}
			});
		}
	};
	//通过jQuery对象初始化分页对象
	$.fn.paging = function(options) {

		return new Paging($(this), options);
	}
})(jQuery, window, document);