package main.java;

public class LocaleConfig {
    private static LocaleConfig localeInstance = new LocaleConfig();
    /**
     * locale 정보를 전역적으로 사용하기 위해 싱글톤으로 구현
     * Locale = 1 한국어
     * Locale = 2 영어
     */
    private int localeNum =1;

    public int getLocaleNum() {
        return localeNum;
    }

    public void setLocaleNum(int localeNum) {
        this.localeNum = localeNum;
    }

    private LocaleConfig(){
        // 싱글톤을 위해 생성자를 private 로 막아준다.
    }
    public static LocaleConfig getLocaleInstance(){
        return localeInstance;
    }
}
