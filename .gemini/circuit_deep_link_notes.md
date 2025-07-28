# Circuit 딥링크 처리 관련 참고 사항

이 문서는 Gemini CLI와의 대화 중 Circuit 라이브러리의 딥링크 처리 방식에 대해 논의하고 확인된 내용을 정리한 것입니다.

## 1. Circuit 공식 문서 (초기 백스택 구성)

*   **링크:** `https://slackhq.github.io/circuit/deep-linking-android/`
*   **주요 내용:** 앱이 처음 실행될 때 `Intent.data`를 파싱하여 `List<Screen>`을 생성하고, 이를 `rememberSaveableBackStack`의 초기값으로 사용하여 백스택을 구성하는 방법을 설명합니다.
*   **특징:** `onNewIntent`를 통한 동적 딥링크 처리나 `CircuitDeepLink` 인터페이스에 대한 직접적인 언급은 없습니다.

## 2. Circuit의 동적 딥링크 처리 메커니즘

*   **개념:** Circuit 라이브러리는 `CircuitDeepLink` 인터페이스와 `Navigator.goTo(Uri)` 메소드를 통해 동적인 딥링크 처리를 지원합니다.
*   **`CircuitDeepLink` 인터페이스:**
    *   특정 URI 패턴(`uriPattern`)을 정의하고, 해당 URI로부터 `Screen` 객체를 생성하는 `createScreen(uri: Uri)` 메소드를 가집니다.
    *   Hilt의 멀티바인딩(`@Binds @IntoSet`)을 통해 Circuit에 등록됩니다.
    *   **주의:** 이 인터페이스는 Circuit `0.29.0` 버전부터 `circuit-deeplink` 모듈로 이동되었습니다. (현재 프로젝트에는 `circuit-deeplink` 모듈이 명시적으로 추가되어 있지 않음)
*   **`Navigator.goTo(Uri)` 메소드:**
    *   `Navigator` 인터페이스의 확장 함수 형태로 제공될 수 있습니다.
    *   내부적으로 등록된 `CircuitDeepLink` 구현체들을 순회하며 주어진 URI를 처리할 수 있는 핸들러를 찾아 `Screen`을 생성하고, 해당 `Screen`으로 네비게이션을 수행합니다.

## 3. `MainActivity`의 딥링크 처리 (singleTask launchMode 고려)

*   **문제:** `MainActivity`가 `android:launchMode="singleTask"`로 설정되어 있어, 앱이 이미 실행 중일 때 새로운 딥링크가 들어오면 `onCreate`가 아닌 `onNewIntent`가 호출됩니다.
*   **해결 패턴:** `onNewIntent`에서 받은 `Intent.data` (URI)를 `MutableStateFlow<Uri?>`에 저장하고, Composable 스코프 내의 `LaunchedEffect`에서 이 `MutableStateFlow`를 관찰하여 `navigator.goTo(uri)`를 호출하는 패턴을 사용합니다.

## 4. 소셜 로그인 딥링크 일반화

*   **`AuthCallbackScreen`:** 특정 소셜 로그인 제공자(예: Apple)에 종속되지 않고, `provider`와 `token`을 파라미터로 받는 일반화된 콜백 `Screen`입니다.
*   **`SocialLoginCircuitDeepLink`:** `https://goods.sytes.net/app/auth/{provider}/callback.*` 형태의 URI 패턴을 처리하여 `AuthCallbackScreen`을 생성합니다.
*   **로그인 후 네비게이션:** `AuthCallbackScreen`의 Presenter에서 토큰 처리 후, `navigator.resetRoot(HomeScreen)`을 호출하여 백스택을 초기화하고 메인 화면으로 이동합니다.

## 5. `AppleLoginWithCustomTabsActivity` 제거

*   별도의 Activity 없이 Jetpack Compose 내에서 Custom Tabs를 실행하고 딥링크 콜백을 처리하는 방향으로 변경되었습니다.
