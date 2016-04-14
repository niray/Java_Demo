package html2lequ;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.xsoup.Xsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main implements PageProcessor {
    // ============================= 运行之前, 改这里
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
    public static final String DIR = "3131951";


    //    public static final String URL = "http://www.mafengwo.cn/i/1145559.html";
    public static final String URL = "http://www.mafengwo.cn/i/" + DIR + ".html";
    private Site site = Site.me().setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");

    public static void main(String[] args) {
        Spider.create(new Main()).addUrl(URL).addPipeline(new JsonFilePipeline("./")).run();
    }

    @Override
    public void process(Page page) {

        try {
            File dir = new File("/Users/goreyjp/Documents/javaWorkspace/TravelTransporter/" + DIR);
            if (!dir.exists()) {
                dir.mkdir();
            }

            String fromDir = "/Users/goreyjp/Documents/javaWorkspace/TravelTransporter/tool/.*";
            String toDir = "/Users/goreyjp/Documents/javaWorkspace/TravelTransporter/" + DIR;

            Runtime.getRuntime().exec(new String[]{"cp", "-c", fromDir + " " + toDir});


            BufferedWriter dateWriter = new BufferedWriter(new FileWriter(new File("/Users/goreyjp/Documents/javaWorkspace/TravelTransporter/" + DIR + "/date.txt")));
            BufferedWriter titleWriter = new BufferedWriter(new FileWriter(new File("/Users/goreyjp/Documents/javaWorkspace/TravelTransporter/" + DIR + "/head.txt")));
            BufferedWriter bodyWriter = new BufferedWriter(new FileWriter(new File("/Users/goreyjp/Documents/javaWorkspace/TravelTransporter/" + DIR + "/body.txt")));
            BufferedWriter imageWriter = new BufferedWriter(new FileWriter(new File("/Users/goreyjp/Documents/javaWorkspace/TravelTransporter/" + DIR + "/images.txt")));
            BufferedWriter wgetWriter = new BufferedWriter(new FileWriter(new File("/Users/goreyjp/Documents/javaWorkspace/TravelTransporter/" + DIR + "/wget.sh")));

            // 出发日期
            Date startDate = new Date();
            String dateStart = "";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            String dateStartStr;
            if (page.getHtml().css("section.note_tags").css("li").all().size() > 0) {
                dateStartStr = page.getHtml().css("section.note_tags").css("li").all().get(0);
                Document docDate = Jsoup.parse(dateStartStr);
                dateStart = docDate.getElementsByTag("span").html();
                startDate = df.parse(dateStart); // 出发的第一天
            } else if (page.getHtml().css("section.top_person").css("span.time").all().size() > 0) {
                dateStartStr = page.getHtml().css("section.top_person").css("span.time").all().get(0);
                Document docDate = Jsoup.parse(dateStartStr);
                dateStart = docDate.getElementsByTag("span").html().substring(0, 10);
                startDate = df.parse(dateStart); // 出发的第一天
            }

            dateWriter.write(0 + ":" + 1 + ":" + dateStart + ":" + startDate.getTime() / 1000 + "\n");

            // 大图
            String bannderImage = page.getHtml().css("div.tp_img").css("img").all().get(0);
            Document docBannerImage = Jsoup.parse(bannderImage);
            String bannerImage = docBannerImage.getElementsByTag("img").attr("src");

            // 大标题
            String topTitleHtml = page.getHtml().css("div.tp_title").all().get(0);
            Document docTitle = Jsoup.parse(topTitleHtml);
            String topTitle = docTitle.body().getElementsByTag("h1").html();

            // 标题信息写到文件
            titleWriter.write(topTitle + "\n");
            titleWriter.write("wget " + bannerImage + " -O head.jpeg \n");
            titleWriter.write(dateStart + "\n");

            // 标题信息写到文件
            wgetWriter.write("wget " + bannerImage + " -O head.jpeg \n");

            // 每一个分页内容
            List<String> allText = page.getHtml().css("div.ard_list").css("p").all();
            List<String> allImage = page.getHtml().css("div.ard_list").css("div.ard_pic").all();
            List<String> allDate = page.getHtml().css("div.ard_list").css("h2").all();

            int i = 0;
            for (i = 0; i < allText.size(); i++) {
                String strText = allText.get(i);
                Document p = Jsoup.parse(strText);

                String nodeIndex = p.body().getElementsByClass("dn").attr("data-nodeindex");
                String text = Xsoup.compile("[@class='_j_renditem']/text()").evaluate(p).get();

                System.out.println(nodeIndex + ":" + text);
                bodyWriter.write(String.format("%04d", Integer.parseInt(nodeIndex)) + ":" + text + "\n");

            }

            int j = 0;
            for (j = 0; j < allImage.size(); j++) {
                String strText = allImage.get(j);
                Document p = Jsoup.parse(strText);

                String nodeIndex = p.body().getElementsByClass("dn").attr("data-nodeindex");
                String imageUrl = p.body().getElementsByClass("_j_lazyimg").attr("data-url");

                System.out.println(nodeIndex + ":" + imageUrl);
                bodyWriter.write(String.format("%04d", Integer.parseInt(nodeIndex)) + ":" + imageUrl + "\n");

                imageWriter.write(imageUrl + "\n");
                wgetWriter.write("wget " + imageUrl + "\n");
            }

            int k = 0;
            for (k = 0; k < allDate.size(); k++) {
                String strDate = allDate.get(k);
                Document p = Jsoup.parse(strDate);

                String nodeIndex = p.body().getElementsByClass("_j_renditem").attr("data-nodeindex");
                String dayIndex = p.body().getElementsByClass("_j_renditem").attr("data-index");

                Date day = new Date(startDate.getTime() + (Integer.parseInt(dayIndex) - 1) * 24 * 60 * 60 * 1000);
                String strDay = df.format(day);

                dateWriter.write(nodeIndex + ":" + dayIndex + ":" + strDay + ":" + day.getTime() / 1000 + "\n");
            }

            bodyWriter.close();
            wgetWriter.close();
            imageWriter.close();
            dateWriter.close();
            titleWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
