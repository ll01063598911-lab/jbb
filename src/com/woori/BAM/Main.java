package com.woori.BAM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // static 멤버 필드
    static int lastArticleId = 1;  // lastArticleId --> 전역변수
    static List<Article> articles = new ArrayList<>();  //articles --> 전역변수


    public static void main(String[] args) {

        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);
        //일반 메서드는 호출 불가
        makeTestData();   //  makeTestData() --> 메서드가 static 메서드 일수 밖에 없는 이유

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
                System.out.println("번호  |   제목   |  내용  |       regDate    |   조회수");
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);
                    System.out.printf("%d    |    %s   |  %s  |   %s  |  %d\n", article.id, article.title, article.body, article.regDate , article.viewCnt );  //static 메서드 호출
                }
            } else if (cmd.equals("article write")) {
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();
                System.out.println(lastArticleId + " 번글이 생성되었습니다");

                Article article = new Article(lastArticleId, title, body, Util.getDateStr(),0 ); // viewCnt --> write 실행, 저장 --> viewCnt --> 0 이다

                articles.add(article);
                lastArticleId++;
            } else if (cmd.startsWith("article detail")) {
                String[] cmdBits = cmd.split(" ");
                Article foundArticle = null;
                int id = 0;

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
                        foundArticle = article;
                        break;  //for문을 빠져나감
                    }
                }
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
                    continue; //while문을 다시 시작해라
                }

                foundArticle.increaseViewCnt();

                // serach후 detail 내용 출력
                System.out.println("번호 : " + foundArticle.id);
                System.out.println("날짜 : " + foundArticle.regDate); //날짜 + 시간 출력
                System.out.println("제목 : " + foundArticle.title);
                System.out.println("내용 : " + foundArticle.body);
                System.out.println("조회수 : " + foundArticle.viewCnt);

            } else if (cmd.startsWith("article modify")) {
                String[] cmdBits = cmd.split(" ");
                Article foundArticle = null;
                int id = 0;

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
                        foundArticle = article;
                        break;  //for문을 빠져나감
                    }
                }
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
                    continue; //while문을 다시 시작해라
                }
                // serach후 modify 내용 출력
                System.out.print("수정할 제목 :" );
                String title = sc.nextLine().trim();
                System.out.print("수정할 내용 :" );
                String body = sc.nextLine().trim();

                foundArticle.title = title;
                foundArticle.body = body;
                System.out.println(id + "번 게시물이 수정되었습니다");

            } else if (cmd.startsWith("article delete")) {
                String[] cmdBits = cmd.split(" ");
                Article foundArticle = null;
                int id = 0;

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
                        foundArticle = article;
                        break;  //for문을 빠져나감
                    }
                }
                if (foundArticle == null) { //serarch 수행했으나 게시글이 없음
                    System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
                    continue; //while문을 다시 시작해라
                }
                // serach후 delete
//                articles.remove(id-1);        //remove(int index) 1번 방식
                articles.remove(foundArticle);  //remove(Object e)  2번 방식 --> 권장

                System.out.println(id + "번 게시물이 삭제되었습니다");

            } else {
                System.out.println("존재하지 않는 명령어 입니다");
            }
        }
        System.out.println("== 프로그램 종료 ==");
    }

    static void makeTestData() {  // static 메서드안에서는 static 멤버 필드를 사용한다, 일반 멤버 필드는 사용불가
        for(int i = 1; i <= 500 ; i++ ) {
            articles.add(new Article(lastArticleId++,"제목" + i, "내용" + i, Util.getDateStr(), i * 10)) ;
        }
    }
}

class Article {
    int id;
    String title;
    String body;
    String regDate;
    int viewCnt;

    public Article(int lastArticleID, String title, String body, String regDate , int viewCnt) {
        this.id = lastArticleID;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
        this.viewCnt = viewCnt;
    }

    void increaseViewCnt() {
        this.viewCnt++ ;
    }
}