// ドキュメントの読み込みが完了したら実行される関数
document.addEventListener('DOMContentLoaded', function() {
    // 全てのフォームを取得
    const forms = document.querySelectorAll('form');
    // ハンバーガーメニューの要素を取得
    const hamburgerMenu = document.querySelector('.hamburger-menu');
    // サイドバーの要素を取得
    const sidebar = document.querySelector('.sidebar');
    // サイドバーを閉じるボタンの要素を取得
    const closeBtn = document.querySelector('.close-btn');

    // 各フォームに対して、送信時のバリデーションを設定
    forms.forEach(function(form) {
        form.addEventListener('submit', function(event) {
            if (!validateForm(form)) {
                // フォームが無効な場合、送信を中止
                event.preventDefault();
            }
        });
    });

    // ハンバーガーメニューをクリックしたとき、サイドバーを表示
    hamburgerMenu.addEventListener('click', function() {
        sidebar.style.width = '250px';
    });

    // サイドバーの閉じるボタンをクリックしたとき、サイドバーを非表示
    closeBtn.addEventListener('click', function() {
        sidebar.style.width = '0';
    });
});

// フォームのバリデーションを行う関数
function validateForm(form) {
    let valid = true;
    // 必須入力のフィールドを全て取得
    const inputs = form.querySelectorAll('input[required], textarea[required]');
    inputs.forEach(function(input) {
        if (!input.value.trim()) {
            // フィールドが空の場合、エラーメッセージを表示し、バリデーションを無効に
            valid = false;
            showError(input, 'このフィールドは必須です');
        } else {
            // フィールドに値がある場合、エラーメッセージをクリア
            clearError(input);
        }
    });
    return valid;
}

// エラーメッセージを表示する関数
function showError(input, message) {
    const error = input.nextElementSibling;
    if (error && error.classList.contains('error-message')) {
        // 既存のエラーメッセージを更新
        error.textContent = message;
    } else {
        // 新しいエラーメッセージを作成
        const errorMessage = document.createElement('div');
        errorMessage.className = 'error-message';
        errorMessage.textContent = message;
        input.parentNode.insertBefore(errorMessage, input.nextSibling);
    }
    input.classList.add('error');
}

// エラーメッセージをクリアする関数
function clearError(input) {
    const error = input.nextElementSibling;
    if (error && error.classList.contains('error-message')) {
        // エラーメッセージを削除
        error.remove();
    }
    input.classList.remove('error');
}
