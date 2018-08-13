package com.ousy.scorea.business;

/**
 * Created by ousyy on 2018/6/20.
 */

public class SourceFun
{
    public SourceFun()
    {
    }

    /*
     * 营业收入增长率指标分
     * @param income 单位%
     * @return
     */
    public int incomeFun(float income)
    {
        int result = 0;
        result = (int) (income - 10 + 0.5);
        result += 5;
        result = section(result);

        return result;
    }

    /**
     * @param profit     营业利润增长率
     * @param lastProfit 上期营业利润增长率
     * @return
     */
    public int profitFun(float profit, float lastProfit)
    {
        int result = 0;
        float rate = 0;
        rate = (profit - lastProfit) / Math.abs(lastProfit) * 100;
        result = (int) ((rate - 20) * 0.5 + 0.5);
        result += 5;
        result = section(result);

        return result;
    }

    /**
     * 单位%
     *
     * @param margin  以下是前三年年度的毛利率
     * @param margin1
     * @param margin2
     * @param margin3
     * @return
     */
    public int marginFun(float margin, float margin1, float margin2, float margin3)
    {
        int result = 0;
        float lastMargin = 0;
        lastMargin = (margin1 + margin2 + margin3) / 3;
        result = (int) ((margin - lastMargin) * 2 + 0.5);
        result += 5;
        result = section(result);

        return result;
    }

    /**
     * @param costRate  前三年度的期间费用率
     * @param costRate1
     * @param costRate2
     * @param costRate3
     * @return
     */
    public int costFun(float costRate, float costRate1, float costRate2, float costRate3)
    {
        int result = 0;
        result = (int) ((costRate - (costRate1 + costRate2 + costRate3) / 3) * 2 + 0.5);
        result = 5 - result;
        result = section(result);

        return result;
    }

    /**
     * 期间费用率
     *
     * @param saleCost 销售费用
     * @param mCost    管理费用
     * @param fCost    财务费用
     * @param income   营业收入
     * @return 单位%
     */
    public float costRate(float saleCost, float mCost, float fCost, float income)
    {
        float result = 0;
        result = (saleCost + mCost + fCost) / income * 100;

        return result;
    }

    /**
     * @param inven
     * @param lastInven 上季度的存货
     *                  前三年度的存货
     * @param inven1
     * @param inven2
     * @param inven3
     * @return
     */
    public int inventoryFun(float inven, float lastInven, float inven1, float inven2, float inven3)
    {
        int result = 0;
        float invenRate = 0;
        // 年化存货周转率
        float yearInven = 0;
        float rate = 0;
        invenRate = (inven1 + inven2 + inven3) / 3;
        if (lastInven != 0)
        {
            yearInven = inven * inven1 / lastInven;
        }
        else
        {
            yearInven = inven;
        }
        rate = (yearInven - invenRate) / invenRate * 100;
        result = (int) (rate * 0.5 + 0.5 + 5);
        result = section(result);

        return result;
    }

    /**
     * @param cash1     上一年的现金流量
     * @param cash2     上两年的现金流量
     * @param cash3
     * @param incomeGu1 上一年的每股收益
     * @param incomeGu2
     * @param incomeGu3
     * @return
     */
    public int cashFun(float cash1, float cash2, float cash3, float incomeGu1, float incomeGu2, float incomeGu3)
    {
        int result = 0;
        float aveCash = 0;
        float aveIncomeGu = 0;
        float rate = 0;
        aveCash = cash1 + cash2 + cash3;
        aveIncomeGu = incomeGu1 + incomeGu2 + incomeGu3;
        rate = (aveCash - aveIncomeGu) / Math.abs(aveIncomeGu) * 100;
        result = (int) (5 + rate * 0.25 + 0.5);
        result = section(result);

        return result;
    }

    public int returnFun(float returnRate, float lastReturnRate, float returnRate1)
    {
        int result = 0;
        float yearRate = 0;
        if (lastReturnRate != 0)
        {
            yearRate = returnRate * returnRate1 / lastReturnRate;
        }
        else
        {
            yearRate = returnRate;
        }
        result = (int) (5 + yearRate - 15 + 0.5);
        result = section(result);

        return result;
    }

    public int rewardFun(float reward, float lastReward, float reward1)
    {
        int result = 0;
        float yearRate = 0;
        if (lastReward != 0)
        {
            yearRate = reward * reward1 / lastReward;
        }
        else
        {
            yearRate = reward;
        }
        result = (int) ((yearRate - 5) * 2 + 0.5);
        result = section(result);

        return result;
    }

    public int pbvFun(float pbv)
    {
        int result = 0;
        result = (int) (5 - (pbv - 3) * 2.5 + 0.5);
        result = section(result);

        return result;
    }

    public int pegFun(float pe, float profit1, float profit2, float profit3)
    {
        int result = 0;
        float aveProfit = 0;
        float peg = 0;
        aveProfit = (profit1 + profit2 + profit3) / 3;
        peg = pe / aveProfit;
        result = (int) (5 - (peg - 1) * 10 + 0.5);
        result = section(result);

        return result;
    }

    // result只能在0到10取值
    private int section(int result)
    {
        if (result > 10)
        {
            return 10;
        }
        else if (result < 0)
        {
            return 0;
        }

        return result;
    }
}
