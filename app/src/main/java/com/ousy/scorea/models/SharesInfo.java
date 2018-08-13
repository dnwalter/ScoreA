package com.ousy.scorea.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by ousyy on 2018/6/18.
 */

@DatabaseTable(tableName = "sharesInfo")
public class SharesInfo implements Serializable
{
    @DatabaseField(generatedId = true)
    private int id;
    // 行业
    @DatabaseField
    private String type;
    // 股票名
    @DatabaseField
    private String name;
    // 股票号
    @DatabaseField
    private String num;
    // 例如：2018-1，代表的是2018年第一季度
    @DatabaseField
    private String date;
    // 总分
    @DatabaseField
    private int score;
    // 营业收入增长率
    @DatabaseField
    private int incomeRate;
    // 营业利润增长率
    @DatabaseField
    private int profitRate;
    // 毛利率
    @DatabaseField
    private int margin;
    // 期间费用率
    @DatabaseField
    private int costRate;
    // 存货周转率
    @DatabaseField
    private int inventoryRate;
    // 经营性现金流量
    @DatabaseField
    private int cashFlow;
    // 净资产收益率
    @DatabaseField
    private int returnRate;
    // 总资产报酬率
    @DatabaseField
    private int rewardRate;
    // 市净率
    @DatabaseField
    private int pbv;
    // PEG
    @DatabaseField
    private int peg;

    public SharesInfo()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNum()
    {
        return num;
    }

    public void setNum(String num)
    {
        this.num = num;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getScore()
    {
        return score;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getIncomeRate()
    {
        return incomeRate;
    }

    public void setIncomeRate(int incomeRate)
    {
        this.incomeRate = incomeRate;
    }

    public int getProfitRate()
    {
        return profitRate;
    }

    public void setProfitRate(int profitRate)
    {
        this.profitRate = profitRate;
    }

    public int getMargin()
    {
        return margin;
    }

    public void setMargin(int margin)
    {
        this.margin = margin;
    }

    public int getCostRate()
    {
        return costRate;
    }

    public void setCostRate(int costRate)
    {
        this.costRate = costRate;
    }

    public int getInventoryRate()
    {
        return inventoryRate;
    }

    public void setInventoryRate(int inventoryRate)
    {
        this.inventoryRate = inventoryRate;
    }

    public int getCashFlow()
    {
        return cashFlow;
    }

    public void setCashFlow(int cashFlow)
    {
        this.cashFlow = cashFlow;
    }

    public int getReturnRate()
    {
        return returnRate;
    }

    public void setReturnRate(int returnRate)
    {
        this.returnRate = returnRate;
    }

    public int getRewardRate()
    {
        return rewardRate;
    }

    public void setRewardRate(int rewardRate)
    {
        this.rewardRate = rewardRate;
    }

    public int getPbv()
    {
        return pbv;
    }

    public void setPbv(int pbv)
    {
        this.pbv = pbv;
    }

    public int getPeg()
    {
        return peg;
    }

    public void setPeg(int peg)
    {
        this.peg = peg;
    }
}
