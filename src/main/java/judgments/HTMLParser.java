package judgments;

import judgments.ApiModel.Attributes.*;
import judgments.ApiModel.Judgment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HTMLParser {

    /*
    parsuje sygnaturę
     */
    private List<CourtCase> signature(Document html){
        String string = html.select("div#warunek").text();
        int index = string.indexOf("-");
        string = string.substring(0,index - 1);
        CourtCase courtCase = new CourtCase(string);
        List<CourtCase> courtCases = new ArrayList<>();
        courtCases.add(courtCase);
        return courtCases;
    }
    /*
    parsuje uzasadnienie
     */
    private String  textContent(Elements elements){
        String result = "";
        for(Element element : elements){
            if(element.text().contains("Uzasadnienie")){
                result =  element.text().substring(13);
                break;
            }
        }
        return result;
    }
    /*
    parsuje sędziów
     */
    private List<Judge> judges(Elements elements){
        List<Judge> result = new ArrayList<>();
        for(Element element : elements){
            if(element.text().contains("Sędziowie")){
                String [] judges = element.select("td.info-list-value").html().split("<br>");
                for(String string : judges){
                    Judge judge = new Judge();
                    judge.setName(name(string));
                    judge.setSpecialRoles(role(string));
                    result.add(judge);
                }
                break;
            }
        }
        return result;
    }
    /*
    parsuje imię i nazwisko sędziego z tekstu html
     */
    private String name(String string){
        return string.replaceFirst(" /(.*?)/","");
    }
    /*
    parsuje role sędziego z tekstu html
     */
    private List<SpecialRole> role(String string){
        List<SpecialRole> result = new ArrayList<>();
        if(!string.contains("/")){
            return null;
        }
        int beg = string.indexOf("/");
        int end = string.lastIndexOf("/");
        String role = string.substring(beg,end);
        if(role.contains("sprawozdawca") || role.contains("Sprawozdawca")){
            result.add(SpecialRole.REPORTING_JUDGE);
        }
        if(role.contains("autor uzasadnienia")){
            result.add(SpecialRole.REASONS_FOR_JUDGMENT_AUTHOR);
        }
        if(role.contains("przewodniczący") || role.contains("Przewodniczący")){
            result.add(SpecialRole.PRESIDING_JUDGE);
        }
        return result;
    }
    /*
    parsuje rodzaj sądu z pliku html
     */
    private CourtType courtType(Elements elements){
        CourtType court = null;
        for(Element element : elements){
            if(element.text().contains("Sąd")){
                if(element.text().contains("Naczelny")){
                    court = CourtType.LEADING_ADMINISTRATIVE;
                }
                else {
                    court = CourtType.PROVINCIAL_ADMINISTRATIVE;
                }
                break;
            }
        }
        return court;
    }
    /*
    parsuje datę orzeczenia wyroku
     */
    private Source source(Elements elements){
        Source source = new Source();
        String date = "";
        for(Element element : elements){
            if(element.text().contains("Data orzeczenia")){
                date = element.select("td.info-list-value").get(0).text().substring(0,10);
                break;
            }
        }
        source.setPublicationDate(date);
        return source;
    }
    /*
    parsuje powołane przepisy
     */
    private List<ReferencedRegulation> referencedRegulations(Elements elements){
        List<ReferencedRegulation> referencedRegulations = new ArrayList<>();
        for (Element element : elements){
            if (element.text().contains("Powołane przepisy")){
                String x = element.select("td.info-list-value").html();
                Elements table = Jsoup.parse(x).select("a");
                Elements table2 = Jsoup.parse(x).select("span.nakt");
                for (int i = 0 ; i < table.size() ; i++){
                    ReferencedRegulation r = new ReferencedRegulation();
                    r.setJournalTitle(table.get(i).html() + " - " + table2.get(i).html());
                    referencedRegulations.add(r);
                }
                break;
            }
        }
        return referencedRegulations;
    }
    /*
    Parsuje jeden plik HTML do jednego obiektu Judgment
     */
    private Judgment parseFile(String content){
        Document htmlDocument = Jsoup.parse(content, "UTF-8");
        Elements elements = htmlDocument.select("tr.niezaznaczona");
        Judgment judgment = new Judgment();
        judgment.setCourtCases(signature(htmlDocument));
        judgment.setTextContent(textContent(elements));
        judgment.setJudges(judges(elements));
        judgment.setSource(source(elements));
        judgment.setCourtType(courtType(elements));
        judgment.setReferencedRegulations(referencedRegulations(elements));
        return judgment;
    }
    /*
    parsuje wszystkie pliki html
     */
    public ArrayList<Judgment> parseFiles(ArrayList<String> fileContent) {
        ArrayList<Judgment> judgments = new ArrayList<>();
        for (String file : fileContent){
            Judgment judgment = parseFile(file);
            judgments.add(judgment);
        }
        return judgments;
    }
    /*
    wkłada wszystkie obiekty Judgment do mapy
     */
    public HashMap<String,Judgment> parseToMap(ArrayList<Judgment> judgments){
        HashMap<String,Judgment> map = new HashMap<>();
        for (Judgment judgment : judgments){
            map.put(judgment.getCourtCases().get(0).getCaseNumber(),judgment);
        }
        return map;
    }

}
