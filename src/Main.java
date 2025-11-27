public class Main {
    public static void main(String[] args) {
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
        System.out.println(new Article().id);
    }
}

class Article {
    static int lastId;   //static 초기화 블럭에서 사용됨
    int id;

    static {             //static 초기화 블럭, 프로그램 시작시 1회 / 메모리 상단에 위치함(제일먼저 실행됨)
        lastId = 0;
    }

    Article() {             // default 생성자
        this(lastId + 1);   // this() --> 다른 생성자 호출
        lastId++;
    }

    Article(int id) {       //생성자 오버로딩
        this.id = id;
    }

}