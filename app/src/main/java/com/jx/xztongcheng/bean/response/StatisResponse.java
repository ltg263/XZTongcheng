package com.jx.xztongcheng.bean.response;

import java.util.List;

/**
 * Create by Sxl on 2021/3/9.
 */
public class StatisResponse {

    private List<ListBean> statisticalDetailDTOList;

    public static class ListBean {
        private String date;
        private String contractMoneys;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getContractMoneys() {
            return contractMoneys;
        }

        public void setContractMoneys(String contractMoneys) {
            this.contractMoneys = contractMoneys;
        }
    }

    public List<ListBean> getStatisticalGraphDTOList() {
        return statisticalDetailDTOList;
    }

    public void setStatisticalGraphDTOList(List<ListBean> statisticalGraphDTOList) {
        this.statisticalDetailDTOList = statisticalGraphDTOList;
    }
}
