package com.petid.domain.member.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNameUtil {

    private final Random random = new Random();

    private static final String[] adjectives = {
            "친절한", "성실한", "정직한", "느긋한", "용감한", "예민한", "침착한", "명랑한", "자상한",
            "이기적인", "불친절한", "활발한", "냉정한", "따뜻한", "겁많은", "아름다운", "멋진",
            "귀여운", "잘생긴", "키가 큰", "키가 작은", "마른", "통통한", "날씬한", "단정한",
            "깔끔한", "우아한", "화려한", "기쁜", "슬픈", "화난", "행복한", "우울한", "놀란",
            "흥분된", "지친", "걱정스러운", "만족스러운", "실망한", "짜증난", "두려운", "안타까운",
            "깨끗한", "깔끔한", "젖은", "마른", "건강한", "방구뀌는", "새로운", "낡은", "빠른", "느린",
            "시끄러운", "조용한", "차가운", "따뜻한", "무거운", "가벼운", "거대한", "작은",
            "큰", "낮은", "긴", "짧은", "두꺼운", "얇은", "깊은", "얕은",
            "빨간", "파란", "노란", "초록", "검은", "하얀", "회색의", "갈색의", "보라색의",
            "주황색의", "분홍색의", "열정적인", "창의적인", "겸손한", "배려심 깊은", "야심찬", "신중한",
            "외향적인", "내성적인", "도전적인", "낙천적인", "현명한"
    };

    private static final String[] dogBreeds = {
            "포메라니안", "비글", "푸들", "골든 리트리버", "시베리안 허스키", "불독", "치와와",
            "닥스훈트", "말티즈", "코커 스패니얼", "셰퍼드", "보더 콜리", "래브라도 리트리버",
            "시추", "웰시 코기", "도베르만", "로트와일러", "요크셔 테리어", "아키타", "바센지",
            "비숑 프리제", "그레이하운드", "샤페이", "달마시안", "세인트 버나드", "차우차우",
            "퍼그", "프렌치 불독", "잭 러셀 테리어", "파피용", "사모예드", "셰틀랜드 쉽독",
            "불독", "슈나우저", "아프간 하운드", "말라뮤트", "그레이트 데인",
            "베들링턴 테리어", "페키니즈", "잉글리시 세터", "그레이하운드", "비즐라",
            "골든두들", "스탠다드 푸들", "미니어처 슈나우저", "셰퍼드", "시바견"
    };

    public String getRandomName() {
        String randomAdjective = adjectives[random.nextInt(adjectives.length)];
        String randomDogBreed = dogBreeds[random.nextInt(dogBreeds.length)];

        return randomAdjective + " " + randomDogBreed;
    }
}
