package hospital_web.util;

import javax.servlet.http.HttpServletRequest;

public class CommonPagination {
	// ��һҳ
	private int first;
	// ��һҳ
	private int ascending;
	// ��һҳ
	private int next;
	// ���ҳ
	private int lastly;
	private int pageSize;
	private int pageNo;
	private int pageSum;
	private int sum;
	//����
	private int rowIndex=1;
	public CommonPagination(int pageSize,int sum,HttpServletRequest request){
		this.sum=sum;
		this.pageSize=pageSize;
		String no=request.getParameter("pageNo");
		if(no==null||no.equals("")){
			pageNo=1;
		}else{
			try {
				pageNo=Integer.parseInt(no);
			} catch (Exception e) {
				pageNo=1;
			}
		}
		//�������
		pageSum = sum / pageSize;
		if (pageSum == 0)
			pageSum = 1;
		else if (sum % pageSize > 0) {
			pageSum++;
        }
		first = 1;
		lastly = pageSum;
		ascending = pageNo - 1;
		if (ascending < 1)
			ascending = 1;
		next = pageNo + 1;
		if (next > pageSum)
			next = pageSum;
		rowIndex=pageNo*pageSize-pageSize+1;
	}
	public int getPageSize(){
		return pageSize;
	}
	public int getPageNo(){
		return pageNo;
	}
	public int getPageSum(){
		return pageSum;
	}
	public int getSum(){
		return sum;
	}
	public int getFirst() {
		return first;
	}
	public int getAscending() {
		return ascending;
	}
	public int getNext() {
		return next;
	}
	public int getLastly() {
		return lastly;
	}
	public int getRowIndex() {
		return rowIndex;
	}
}
