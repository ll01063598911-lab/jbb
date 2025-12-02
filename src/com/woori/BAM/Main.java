package com.woori.BAM;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==게시판==");
        Scanner sc = new Scanner(System.in);
        int lastArticleID = 1;
        List<Article> articles = new ArrayList<>();

        while (true) {
            System.out.printf("cmd) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력해 주세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }
            if (cmd.equals("article list")) {
                if (articles.size() == 0) {
                    System.out.println("게시글이 없습니다");
                    continue;
                }
                System.out.println("번호  |   제목 | 내용 | regDate | 조회수 ");
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);
                    System.out.printf("%d    |    %s   |  %s  |   %d\n", article.id, article.title, article.body, article.regDate, article.viewCnt);  //static 메서드 호출
                }
            } else if (cmd.equals("article write")) {
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();
                System.out.println(lastArticleID + " 번글이 생성되었습니다");


                Article article = new Article(lastArticleID, title, body, Util.getDateStr(),0); // 현재 날짜+시간 불러오기 --> Util.getDataStr()

                articles.add(article);
                lastArticleID++;
            } else if (cmd.startsWith("article detail")) {
                String[] cmdBits = cmd.split(" ");
                Article foundArticle = null;
                int id = 0;
                int viewCnt = 0;

                try {
                    id = Integer.parseInt(cmdBits[2]);

                } catch (NumberFormatException e) {
                    System.out.println("정수를 입력하시길 바랍니다");
                    continue;   // while 다시 실행해
                } catch (Exception e) {
                    //(그밖에 모든 Exception 변수명)
                }
                for (Article article : articles) {
                    if (article.id == id) {
                        foundArticle = article;   //search 성공시 article 객체를 --> foundArticle 대입
                        break;  //for문을 빠져나감
                    }
                }
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
                    continue; //while문을 다시 시작해라
                }
                foundArticle.viewCnt++;

                // serach후 detail 내용 출력
                System.out.println("번호 : " + foundArticle.id);
                System.out.println("날짜 : " + foundArticle.regDate); //날짜 + 시간 출력
                System.out.println("제목 : " + foundArticle.title);
                System.out.println("내용 : " + foundArticle.body);
                System.out.println("조회수 : " + foundArticle.viewCnt);

            } else {
                System.out.println("존재하지 않는 명령어 입니다");
            }
        }
        System.out.println("== 프로그램 종료 ==");
    }
}

class Article {
    int id;
    String title;
    String body;
    String regDate;  // 필드 추가 --> yyyy-mm-dd hh:mm:ss
    int viewCnt;



    public Article(int lastArticleID, String title, String body, String regDate, int viewCnt) {
        this.id = lastArticleID;
        this.title = title;
        this.body = body;
        this.regDate = regDate;    // system(PC) 날짜 + 시간 이 String 저장
        this.viewCnt = viewCnt;    // system(PC) 날짜 + 시간 이 String 저장


    }
}