package com.company;

import jxl.Workbook;
import jxl.write.Number;
import jxl.write.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ManyPars {

    private static final String EXCEL_FILE_LOCATION = "d:\\write.xls";

    private static Document getPage(String year, String month) throws IOException{
        String url ="https://www.gismeteo.ru/diary/4368/" + year + "/" + month +"/";
        return Jsoup.parse(new URL(url), 3000);
    }
    public static void vain(String[] args) throws Exception {

        WritableWorkbook myFirstWbook = null;
        try {

            myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

// создаем лист Excel
            WritableSheet excelSheet = myFirstWbook.createSheet("Sheet 1", 0);

// добавить что-то в лист Excel
            Label label = new Label(0, 0, "Дата");
            excelSheet.addCell(label);

            label = new Label(1, 0, "Температура Утром");
            excelSheet.addCell(label);

            label = new Label(2, 0, "Температура Вечером");
            excelSheet.addCell(label);

            label = new Label(3, 0, "Давление Утром");
            excelSheet.addCell(label);

            label = new Label(4, 0, "Давление Вечером");
            excelSheet.addCell(label);

            label = new Label(5, 0, "Скорость ветра Утром");
            excelSheet.addCell(label);

            label = new Label(6, 0, "Скорость ветра Вечером");
            excelSheet.addCell(label);

            label = new Label(7, 0, "Направление ветра Утром");
            excelSheet.addCell(label);

            label = new Label(8, 0, "Направление ветра Вечером");
            excelSheet.addCell(label);

            // Number number = new Number(0, 1, 1);
            //excelSheet.addCell(number);

            //label = new Label(1, 0, "Result");
            //excelSheet.addCell(label);

            //label = new Label(1, 1, "Passed");
            //excelSheet.addCell(label);


            int rowNumDate = 0;
            int rowNumTemp = 0;
            int rowNumTempMorning = 0;
            int rowNumTempEvening = 0;
            int rowNumPressure = 0;
            int rowNumPressureMorning = 0;
            int rowNumPressureEvening = 0;
            int rowNumWind = 0;
            int rowNumWindMorning = 0;
            int rowNumWindEvening = 0;
            int rowNumWindDirection = 0;
            int rowNumWindDirectionMorning = 0;
            int rowNumWindDirectionEvening = 0;


            for (int year = 1998; year < 2021; year++) {
                for (int month = 1; month <= 12; month++) {
                    String yearStr = Integer.toString(year);
                    String monthStr = Integer.toString(month);
                    Document page = getPage(yearStr, monthStr);

                    Element tableWth = page.select("table[align=center]").first();
                    //System.out.println(tableWth);


                    // Даты
                    assert tableWth != null;
                    Elements dates = tableWth.select("td[class=first]");
                    for (Element date : dates) {
                        String dateString = date.select("td[class=first]").text();
                        //System.out.println(dateString);
                        rowNumDate++;
                        String typeDate = dateString + "." + monthStr + "." + yearStr;
                        label = new Label(0, rowNumDate, typeDate);
                        excelSheet.addCell(label);
                        //System.out.println(typeDate);


                    }


                    // Температура
                    Elements temps = tableWth.select("td[class=first_in_group positive], td[class=first_in_group]");
                    for (Element temp : temps) {
                        String tempString = temp.select("td[class=first_in_group positive], td[class=first_in_group]").text();
                        rowNumTemp++;
                        try {
                            int absTemp = Integer.parseInt(tempString);
                            int temp0 = absTemp + 273;

                            if (rowNumTemp % 2 != 0) {
                                rowNumTempMorning++;
                                Number number = new Number(1, rowNumTempMorning, temp0);
                                excelSheet.addCell(number);
                            } else {
                                rowNumTempEvening++;
                                Number number = new Number(2, rowNumTempEvening, temp0);
                                excelSheet.addCell(number);
                            }
                        } catch (NumberFormatException e) {
                            if (rowNumTemp % 2 != 0) {
                                rowNumTempMorning++;
                                Number number = new Number(1, rowNumTempMorning, 0);
                                excelSheet.addCell(number);
                            } else {
                                rowNumTempEvening++;
                                Number number = new Number(2, rowNumTempEvening, 0);
                                excelSheet.addCell(number);
                            }
                        }
                    }

                    // Давление
                    Elements pressures = tableWth.select("td[class=first_in_group positive]+td, td[class=first_in_group]+td");
                    for (Element pressure : pressures) {
                        String pressureString = pressure.select("td").text();
                        rowNumPressure++;
                        try {
                            int intPressure = Integer.parseInt(pressureString);
                            //  System.out.println(intPressure);
                            if (rowNumPressure % 2 != 0) {
                                rowNumPressureMorning++;
                                Number number = new Number(3, rowNumPressureMorning, intPressure);
                                excelSheet.addCell(number);
                            } else {
                                rowNumPressureEvening++;
                                Number number = new Number(4, rowNumPressureEvening, intPressure);
                                excelSheet.addCell(number);
                            }
                        } catch (NumberFormatException e) {
                            if (rowNumPressure % 2 != 0) {
                                rowNumPressureMorning++;
                                Number number = new Number(3, rowNumPressureMorning, 0);
                                excelSheet.addCell(number);
                            } else {
                                rowNumPressureEvening++;
                                Number number = new Number(4, rowNumPressureEvening, 0);
                                excelSheet.addCell(number);
                            }
                        }
                    }
                    // Ветер
                    Elements winds = tableWth.select("span, span + br");
                    for (Element wind : winds) {
                        rowNumWind++;
                        rowNumWindDirection++;
                        String windString = wind.select("span, span + br").text();
                        Matcher m = Pattern.compile("\\d+").matcher(windString);
                        int IntWind;
                        if (m.find()) {
                            IntWind = Integer.parseInt(m.group(0));
                        } else {
                            IntWind = 0;
                        }
                        if (rowNumWind % 2 != 0) {
                            rowNumWindMorning++;
                            Number number = new Number(5, rowNumWindMorning, IntWind);
                            excelSheet.addCell(number);
                        } else {
                            rowNumWindEvening++;
                            Number number = new Number(6, rowNumWindEvening, IntWind);
                            excelSheet.addCell(number);
                        }
                        //System.out.println(windString);
                        int IntWindDirection = 0;
                        if (windString.length() == 1) {
                            IntWindDirection = 0;
                        } else {
                            String WindDirection = "";
                            WindDirection = windString.substring(0, 2);
                            if (WindDirection.equals("C ")) {
                                IntWindDirection = 1;
                            }
                            if (WindDirection.equals("Ю ")) {
                                IntWindDirection = 2;
                            }
                            if (WindDirection.equals("З ")) {
                                IntWindDirection = 3;
                            }
                            if (WindDirection.equals("В ")) {
                                IntWindDirection = 4;
                            }
                            if (WindDirection.equals("ЮЗ")) {
                                IntWindDirection = 5;
                            }
                            if (WindDirection.equals("СВ")) {
                                IntWindDirection = 6;
                            }
                            if (WindDirection.equals("СЗ")) {
                                IntWindDirection = 7;
                            }
                            if (WindDirection.equals("ЮВ")) {
                                IntWindDirection = 8;
                            }
                            if (WindDirection == null) {
                                IntWindDirection = 0;
                            }
                            if (rowNumWindDirection % 2 != 0) {
                                rowNumWindDirectionMorning++;
                                Number number = new Number(7, rowNumWindDirectionMorning, IntWindDirection);
                                excelSheet.addCell(number);
                            } else {
                                rowNumWindDirectionEvening++;
                                Number number = new Number(8, rowNumWindDirectionEvening, IntWindDirection);
                                excelSheet.addCell(number);
                            }
                        }
                    }

                }
            }
            myFirstWbook.write();
        } catch(IOException | WriteException e){
            e.printStackTrace();
        } finally{

            if (myFirstWbook != null) {
                try {
                    myFirstWbook.close();
                } catch (IOException | WriteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



