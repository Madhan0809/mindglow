package com.salmon.test.models.b2b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.pageobjects.b2b.checkout.B2BCheckOutBasketPage;

public class AccountSimpleTable {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountSimpleTable.class);
    private WebElement simpleTable;
    private By tableHeader=By.cssSelector("thead>tr>th");
    private By tableRow=By.cssSelector("tbody>tr"); 
    
    public AccountSimpleTable(WebElement accountSimpleTable) {
        this.simpleTable = accountSimpleTable;
    }
    
    public List<String> getTableHeaderColoums(){
    	return simpleTable.findElements(tableHeader).stream().map(col->col.getText().trim()).filter(text->!(text.isEmpty())).collect(Collectors.toList());
    }
    
    public List<Map<String,String>> getAllTableContent(){
		List<Map<String,String>> tableDetailInfo=new ArrayList<Map<String,String>>();
		if(simpleTable.findElements(tableRow).size()==0){
			return null;
		}
		for(int i=0;i<simpleTable.findElements(tableRow).size();i++){
			WebElement row=simpleTable.findElements(tableRow).get(i);
			tableDetailInfo.add(getTableRowInfo(row));
		}
		LOG.info("Permission table: "+tableDetailInfo);
		return tableDetailInfo;
    }
    
    public List<String> getColumnData(String columnName) {
        List<String> allColumns = getTableHeaderColoums();
        for (int i = 0; i < allColumns.size(); i++) {
            if (allColumns.get(i).equals(columnName)) {
                LOG.info("Return all data under column: " + columnName);
                String targetColumnLocator = "tr[class^='table-row']>td:nth-of-type(" + (i + 1) + ")";
                return simpleTable.findElements(By.cssSelector(targetColumnLocator)).stream()
                        .map(col -> col.getText().trim()).collect(Collectors.toList());
            }
        }
        LOG.error("Column is not found in the table: " + columnName);
        return null;
    }

    private Map<String,String> getTableRowInfo(WebElement row){
		Map<String,String> rowInfo=new HashMap<String,String>();
		for(int i=0;i<simpleTable.findElements(tableHeader).size();i++){
			String colName=simpleTable.findElements(tableHeader).get(i).getText().trim();
			if(colName.isEmpty()){
				continue;
			}
			rowInfo.put(colName, row.findElements(By.cssSelector("td")).get(i).getText().trim().replaceAll("[\r\n]+", ""));
		}
		return rowInfo;
	}
}
