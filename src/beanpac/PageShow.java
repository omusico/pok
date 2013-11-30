package beanpac;

public class PageShow{
		
	private int pageRecordNum = 10;
	private int startRow = 0; //开始显示记录的编号
	private int currentPage=0;//当前页
	private int recordTotal=0;//总记录数;
	private int maxPage=0;//总页数
	private int prevPage=0;//前一页
	private int nextPage=0;//下一页
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageRecordNum() {
		return pageRecordNum;
	}
	public void setPageRecordNum(int pageRecordNum) {
		this.pageRecordNum = pageRecordNum;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getRecordTotal() {
		return recordTotal;
	}
	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}

}
