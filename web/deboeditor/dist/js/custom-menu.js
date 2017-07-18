(function () {

    // ��ȡ wangEditor ���캯���� jquery
    var E = window.wangEditor;
    var $ = window.jQuery;

    // �� createMenu ���������˵�
    E.createMenu(function (check) {

        // ����˵�id����Ҫ�������˵�id�ظ����༭���Դ������в˵�id����ͨ������������-�Զ���˵���һ�ڲ鿴
        var menuId = 'indent';

        // check�����˵����ã�����������-�Զ���˵���һ�����������Ƿ�ò˵�id�����û�У����������Ĵ��롣
        if (!check(menuId)) {
            return;
        }

        // this ָ�� editor ��������
        var editor = this;

        // ���� menu ����
        var menu = new E.Menu({
            editor: editor,  // �༭������
            id: menuId,  // �˵�id
            title: '����', // �˵�����

            // ����״̬��ѡ��״̬�µ�dom������ʽ��Ҫ�Զ���
            $domNormal: $('<a href="#" tabindex="-1"><i class="wangeditor-menu-img-indent-left"></i></a>'),
            $domSelected: $('<a href="#" tabindex="-1" class="selected"><i class="wangeditor-menu-img-indent-left"></i></a>')
        });

        // �˵�����״̬�£�������������¼�
        menu.clickEvent = function (e) {
            // �ҵ���ǰѡ�����ڵ� p Ԫ��
            var elem = editor.getRangeElem();
            var p = editor.getSelfOrParentByName(elem, 'p');
            var $p;

            if (!p) {
                // δ�ҵ� p Ԫ�أ������
                return e.preventDefault();
            }
            $p = $(p);

            // ʹ���Զ�������
            function commandFn() {
                $p.css('text-indent', '2em');
            }
            editor.customCommand(e, commandFn);
        };

        // �˵�ѡ��״̬�£�������������¼�
        menu.clickEventSelected = function (e) {
            // �ҵ���ǰѡ�����ڵ� p Ԫ��
            var elem = editor.getRangeElem();
            var p = editor.getSelfOrParentByName(elem, 'p');
            var $p;

            if (!p) {
                // δ�ҵ� p Ԫ�أ������
                return e.preventDefault();
            }
            $p = $(p);

            // ʹ���Զ�������
            function commandFn() {
                $p.css('text-indent', '0');
            }
            editor.customCommand(e, commandFn);
        };

        // ���ݵ�ǰѡ�����Զ�����²˵���ѡ��״̬��������״̬
        menu.updateSelectedEvent = function () {
            // ��ȡ��ǰѡ�����ڵĸ�Ԫ��
            var elem = editor.getRangeElem();
            var p = editor.getSelfOrParentByName(elem, 'p');
            var $p;
            var indent;

            if (!p) {
                // δ�ҵ� p Ԫ�أ�����Ϊδ����ѡ��״̬
                return false;
            }
            $p = $(p);
            indent = $p.css('text-indent');

            if (!indent || indent === '0px') {
                // �õ���p��text-indent ������ 0������Ϊδ����ѡ��״̬
                return false;
            }

            // �ҵ� p Ԫ�أ����� text-indent ���� 0������Ϊѡ��״̬
            return true;
        };

        // ���ӵ�editor������
        editor.menus[menuId] = menu;
    });

})();

(function () {

    // ��ȡ wangEditor ���캯���� jquery
    var E = window.wangEditor;
    var $ = window.jQuery;

     // �� createMenu ���������˵�
    E.createMenu(function (check) {

        // ����˵�id����Ҫ�������˵�id�ظ����༭���Դ������в˵�id����ͨ������������-�Զ���˵���һ�ڲ鿴
        var menuId = 'lineheight';

        // check�����˵����ã�����������-�Զ���˵���һ�����������Ƿ�ò˵�id�����û�У����������Ĵ��롣
        if (!check(menuId)) {
            return;
        }

        // this ָ�� editor ��������
        var editor = this;

        // �������������֧�� lineHeight ������Ҫ��һ��hook
        editor.commandHooks.lineHeight = function (value) {
            var rangeElem = editor.getRangeElem();
            var targetElem = editor.getSelfOrParentByName(rangeElem, 'p,h1,h2,h3,h4,h5,pre');
            if (!targetElem) {
                return;
            }
            $(targetElem).css('line-height', value + '');
        };

        // ���� menu ����
        var menu = new E.Menu({
            editor: editor,  // �༭������
            id: menuId,  // �˵�id
            title: '�и�', // �˵�����
            commandName: 'lineHeight', // ��������

            // ����״̬��ѡ��װ�µ�dom������ʽ��Ҫ�Զ���
            $domNormal: $('<a href="#" tabindex="-1"><i class="wangeditor-menu-img-text-height"></i></a>'),
            $domSelected: $('<a href="#" tabindex="-1" class="selected"><i class="wangeditor-menu-img-text-height"></i></a>')
        });

        // ����Դ
        var data  = {
            // ��ʽ�� 'value' : 'title'
            '1.0': '1.0��',
            '1.5': '1.5��',
            '1.8': '1.8��',
            '2.0': '2.0��',
            '2.5': '2.5��',
            '3.0': '3.0��'
        };

        // Ϊmenu����droplist����
        var tpl = '<span style="line-height:{#commandValue}">{#title}</span>';
        menu.dropList = new E.DropList(editor, menu, {
            data: data,  // ��������Դ
            tpl: tpl  // ����ģ��
        });

        // ���ӵ�editor������
        editor.menus[menuId] = menu;
    });
})();


(function () {

    // ��ȡ wangEditor ���캯���� jquery
    var E = window.wangEditor;
    var $ = window.jQuery;

    // �� createMenu ���������˵�
    E.createMenu(function (check) {

        // ����˵�id����Ҫ�������˵�id�ظ����༭���Դ������в˵�id����ͨ������������-�Զ���˵���һ�ڲ鿴
        var menuId = 'symbol';

        // check�����˵����ã�����������-�Զ���˵���һ�����������Ƿ�ò˵�id�����û�У����������Ĵ��롣
        if (!check(menuId)) {
            return;
        }

        // this ָ�� editor ��������
        var editor = this;

        // ���� menu ����
        var menu = new E.Menu({
            editor: editor,  // �༭������
            id: menuId,  // �˵�id
            title: '����', // �˵�����

            // ����״̬��ѡ��װ�µ�dom������ʽ��Ҫ�Զ���
            $domNormal: $('<a href="#" tabindex="-1"><i class="wangeditor-menu-img-omega"></i></a>'),
            $domSelected: $('<a href="#" tabindex="-1" class="selected"><i class="wangeditor-menu-img-omega"></i></a>')
        });

        // Ҫ����ķ��ţ���������ӣ�
        var symbols = ['��', '��', '��', '��', '��', '��', '��']

        // panel ����
        var $container = $('<div></div>');
        $.each(symbols, function (k, value) {
            $container.append('<a href="#" style="display:inline-block;margin:5px;">' + value + '</a>');
        });

        // ������ŵ��¼�
        $container.on('click', 'a', function (e) {
            var $a = $(e.currentTarget);
            var s = $a.text();

            // ִ�в��������
            editor.command(e, 'insertHtml', s);
        });

        // ���panel
        menu.dropPanel = new E.DropPanel(editor, menu, {
            $content: $container,
            width: 350
        });

        // ���ӵ�editor������
        editor.menus[menuId] = menu;
    });

})();
