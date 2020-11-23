package dao.pojo;

public abstract class BasePojo {
	private String[] fieldType=new String[]{""};
	/** 用户token */
	private String token;
	/** 前台传送的id字符串 */
	public String ids;
	/** 当前页的页码号 */
	private int page=0;
	/** 当前页记录数 */
	private int rows=0;
	/** 当前页记录数 */
	private int limit;
	/** 批量保存数据 */
	private String datas;
	public Object getEntityId(){
		return null;
	}
	public String getIds() {
		return ids;
	}
	public int getPage() {
		return page;
	}
	public int getRows() {
		return rows;
	}
	public int getLimit() {
		return limit;
	}
	public String getDatas() {
		return datas;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setDatas(String datas) {
		this.datas = datas;
	}
	/**
	 * Title:setPage
	 * Description: 将页面page参数赋给本action的page属性
	 * @param 页面page参数
	 */
	public void setPage(int page) {
		this.page = page;
	}
	
	/**
	 * Title:setLimit
	 * Description: 将页面limit参数赋给本action的limit属性
	 * @param 页面ids参数
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	/**
	 * Title:setIds
	 * Description: 对将页面ids参数赋给本action的ids属性
	 * @param 页面ids参数
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
