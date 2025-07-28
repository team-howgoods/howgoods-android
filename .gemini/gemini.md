# Side99 Android 프로젝트

## 프로젝트 개요

이 프로젝트는 Kotlin 기반의 안드로이드 애플리케이션입니다. Gradle을 사용하여 빌드되며, 여러 모듈로 구성된 다중 모듈 아키텍처를 따릅니다. UI는 Jetpack Compose로 작성되었으며, 아키텍처는 Circuit 라이브러리를 활용합니다. 의존성 주입에는 Hilt가 사용되고, 코드 스타일은 Spotless와 ktlint로 관리됩니다.

## 주요 기술 스택

*   **언어:** Kotlin
*   **UI:** Jetpack Compose
*   **아키텍처:** Circuit (딥링크 포함), 클린 아키텍처 (Presentation, Domain, Data 계층 분리), MVVM/MVI (예상)
*   **의존성 주입:** Hilt
*   **비동기 처리:** Coroutines
*   **데이터 저장:** Preferences DataStore (JWT 토큰 등)
*   **네트워킹:** (라이브러리 미확인)
*   **JSON 직렬화:** Kotlinx Serialization
*   **빌드 시스템:** Gradle
*   **CI/CD:** (미확인)
*   **테스팅:** JUnit, Espresso
*   **기타:** Firebase (Crashlytics, Performance, Analytics, Cloud Messaging)

## 모듈 구조

*   `app`: 애플리케이션의 메인 모듈.
*   `core`: 공통 로직 및 UI 요소를 포함하는 모듈.
    *   `designsystem`: 앱의 디자인 시스템 (색상, 타이포그래피, 아이콘 등).
    *   `domain`: 비즈니스 로직 및 데이터 모델.
    *   `data`: (예정) 데이터 소스 구현 및 Repository 구현체.
    *   `ui`: 공통으로 사용되는 UI 컴포넌트.
    *   `navigation`: 딥링크 처리 및 네비게이션 관련 공통 인터페이스.
*   `feature`: 각 기능별 모듈.
    *   `login`: 로그인 기능 모듈.
*   `build-logic`: Gradle 빌드 로직을 관리하는 컨벤션 플러그인.

## 주요 명령어

*   **빌드 (디버그):** `./gradlew assembleDebug`
*   **빌드 (릴리즈):** `./gradlew assembleRelease`
*   **단위 테스트 실행:** `./gradlew test`
*   **UI 테스트 실행:** `./gradlew connectedAndroidTest`
*   **코드 스타일 검사:** `./gradlew spotlessCheck`
*   **코드 스타일 자동 수정:** `./gradlew spotlessApply`