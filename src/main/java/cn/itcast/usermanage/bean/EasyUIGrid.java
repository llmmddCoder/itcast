package cn.itcast.usermanage.bean;

import java.util.List;

public class EasyUIGrid {
    
    private Long totle;
    
    private List<?> rows;
    
    public EasyUIGrid(){}
    
    public EasyUIGrid(Long totle, List<?>rows ){
        this.rows = rows;
        this.totle = totle;
    }

    public Long getTotle() {
        return totle;
    }

    public void setTotle(Long totle) {
        this.totle = totle;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    } 
}
