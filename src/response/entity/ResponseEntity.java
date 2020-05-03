package response.entity;

import response.BaseResponseEntity;

public class ResponseEntity extends BaseResponseEntity {
	public String code = "0011";
	public String msg = "服务器发生异常";
	public Object data = "{}";
	public String page;
	public String pageSize;
	public String totalSize;
	public int hasShowSize;
}

