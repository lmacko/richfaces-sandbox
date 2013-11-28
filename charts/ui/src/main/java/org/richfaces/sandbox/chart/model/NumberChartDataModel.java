package org.richfaces.sandbox.chart.model;

import java.io.Serializable;

/**
 *
 * @author Lukas Macko
 */
public class NumberChartDataModel extends ChartDataModel<Number, Number> implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -6103945987741329298L;

    public NumberChartDataModel(ChartType type){
        super(type);
        switch(type){
            case line:
                strategy = new LineStrategy();
                break;
            case bar:
                strategy = new BarStrategy();
                break;
            default:
                                throw new IllegalArgumentException(type + "not supported by StringChartDataModel" );
        }
    }

    @Override
    public Class getKeyType() {
        return Number.class;
    }

    @Override
    public Class getValueType() {
        return Number.class;
    }

}
