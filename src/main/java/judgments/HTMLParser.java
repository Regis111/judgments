package judgments;

import judgments.Attributes.CourtType;
import judgments.Attributes.Judge;
import judgments.Attributes.ReferencedRegulation;
import judgments.Attributes.Source;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static judgments.Attributes.CourtType.LEADING_ADMINISTRATIVE;
import static judgments.Attributes.CourtType.PROVINCIAL_ADMINISTRATIVE;
import static org.jsoup.Jsoup.parse;

public class HTMLParser {



    private Judgment parseFile(String content){
        Document htmlDocument = Jsoup.parse(content, "UTF-8");
        Elements elements = htmlDocument.select("tr.niezaznaczona");
        Judgment judgment = new Judgment();
        judgment.setTextContent(textContent(elements)); //uzasadnienie
        judgment.setJudges(judges(elements)); // sędziowie
        judgment.setSource(source(elements)); //publicationDate
        judgment.setCourtType(courtType(elements));
        judgment.setReferencedRegulations(referencedRegulations(elements)); // powołane przepisy
        return judgment;
    }

    private String textContent(Elements elements){
        String result = new String();
        for(Element element : elements){
            if(element.text().contains("Uzasadnienie")){
                result =  element.text().substring(13);
            }
        }
        return result;
    }
    private List<Judge> judges(Elements elements){
        List<Judge> result = new ArrayList<>();
        for(Element element : elements){
            if(element.text().contains("Sędziowie")){
                String text = element.text();
                text = text.replaceAll(" \\/(.*?)\\/","").substring(10);
                ArrayList<String> judgesNameList = judgesList(text.split(" "));
                for(String name : judgesNameList){
                    Judge judge = new Judge();
                    judge.setName(name);
                    result.add(judge);
                }
                break;
            }
        }
        return result;
    }
    private ArrayList<String> judgesList(String [] array){
        ArrayList<String> judges = new ArrayList<>();
        for(int i = 0 ; i + 1< array.length ; i = i + 2){
            judges.add(array[i] + " " + array[i+1]);
        }
        return judges;
    }
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

    private Source source(Elements elements){
        Source source = new Source();
        String date = new String();
        for(Element element : elements){
            if(element.text().contains("Data orzeczenia")){
                date = element.text();
                break;
            }
        }
        source.setPublicationDate(date);
        return source;
    }

    private List<ReferencedRegulation> referencedRegulations(Elements elements){
        List<ReferencedRegulation> referencedRegulations = new ArrayList<>();
        for (Element element : elements){
            if (element.text().contains("Powołane przepisy")){
                ReferencedRegulation r = new ReferencedRegulation();
                r.setJournalTitle(element.text());
                referencedRegulations.add(r);
            }
        }
        return referencedRegulations;
    }

    public ArrayList<Judgment> parseFiles(ArrayList<String> fileContent) throws IOException {
        ArrayList<Judgment> judgments = new ArrayList<>();
        for (String file : fileContent){
            Judgment judgment = parseFile(file);
            judgments.add(judgment);
        }
        return judgments;
    }

}
