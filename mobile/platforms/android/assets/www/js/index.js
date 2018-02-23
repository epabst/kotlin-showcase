if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'webclient'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'webclient'.");
}
if (typeof Yested === 'undefined') {
  throw new Error("Error loading module 'webclient'. Its dependency 'Yested' was not found. Please, check whether 'Yested' is loaded prior to 'webclient'.");
}
if (typeof firebase === 'undefined') {
  throw new Error("Error loading module 'webclient'. Its dependency 'firebase' was not found. Please, check whether 'firebase' is loaded prior to 'webclient'.");
}
var webclient = function (_, Kotlin, $module$Yested, $module$firebase) {
  'use strict';
  var toProperty = $module$Yested.net.yested.core.properties.toProperty_eoe559$;
  var NavbarCompletePosition = $module$Yested.net.yested.ext.bootstrap3.NavbarCompletePosition;
  var ContainerWidth = $module$Yested.net.yested.ext.bootstrap3.ContainerWidth;
  var Col$Width$Tn = $module$Yested.net.yested.ext.bootstrap3.Col.Width.Tn;
  var Col$Width$Xs = $module$Yested.net.yested.ext.bootstrap3.Col.Width.Xs;
  var and = $module$Yested.net.yested.ext.bootstrap3.and_da045t$;
  var ButtonSize = $module$Yested.net.yested.ext.bootstrap3.ButtonSize;
  var ButtonLook = $module$Yested.net.yested.ext.bootstrap3.ButtonLook;
  var backToHash = $module$Yested.net.yested.ext.jquery.backToHash_7rqg5j$;
  var Unit = Kotlin.kotlin.Unit;
  var addClass = Kotlin.kotlin.dom.addClass_hhb33f$;
  var appendText = Kotlin.kotlin.dom.appendText_46n0ku$;
  var btsButton = $module$Yested.net.yested.ext.bootstrap3.btsButton_9ea1v6$;
  var nbsp = $module$Yested.net.yested.core.html.nbsp_r4ncir$;
  var span = $module$Yested.net.yested.core.html.span_mvuu9r$;
  var a = $module$Yested.net.yested.core.html.a_1mlqc4$;
  var h3 = $module$Yested.net.yested.core.html.h3_omchdl$;
  var col = $module$Yested.net.yested.ext.bootstrap3.col_33x5o9$;
  var row = $module$Yested.net.yested.ext.bootstrap3.row_48hlih$;
  var navbar = $module$Yested.net.yested.ext.bootstrap3.navbar_sbitb0$;
  var br = $module$Yested.net.yested.core.html.br_y4uc6z$;
  var toString = Kotlin.toString;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var flatMapIfNotNull = $module$Yested.net.yested.core.properties.flatMapIfNotNull_8mviy2$;
  var mapAsDefault = $module$Yested.net.yested.core.properties.mapAsDefault_aa9sb4$;
  var validate = $module$Yested.net.yested.core.properties.validate_y8f1fd$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Col$Width$Sm = $module$Yested.net.yested.ext.bootstrap3.Col.Width.Sm;
  var toState = $module$Yested.net.yested.ext.bootstrap3.toState_sn5tqx$;
  var map = $module$Yested.net.yested.core.properties.map_aa9sb4$;
  var textInput = $module$Yested.net.yested.ext.bootstrap3.textInput_90gb2u$;
  var State = $module$Yested.net.yested.ext.bootstrap3.State;
  var Property = $module$Yested.net.yested.core.properties.Property;
  var btsFormHorizontal = $module$Yested.net.yested.ext.bootstrap3.btsFormHorizontal_hgubt3$;
  var Div = $module$Yested.net.yested.core.utils.Div_hgarvh$;
  var compareByProperty = $module$Yested.net.yested.core.utils.compareByProperty_34mekm$;
  var sortControlWithArrow = $module$Yested.net.yested.ext.bootstrap3.sortControlWithArrow_lkw2up$;
  var th = $module$Yested.net.yested.core.html.th_844ta1$;
  var div = $module$Yested.net.yested.core.html.div_ar2l3c$;
  var tr = $module$Yested.net.yested.core.html.tr_yjtncd$;
  var thead = $module$Yested.net.yested.core.html.thead_86elqq$;
  var sortedWith = $module$Yested.net.yested.core.properties.sortedWith_2aug6r$;
  var Collapse = $module$Yested.net.yested.ext.bootstrap3.Collapse;
  var utils = $module$Yested.net.yested.core.utils;
  var td = $module$Yested.net.yested.core.html.td_844ta1$;
  var tbody = $module$Yested.net.yested.core.html.tbody_tqipv2$;
  var table = $module$Yested.net.yested.core.html.table_4xa6d$;
  var minus = Kotlin.kotlin.collections.minus_q4559j$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var zip = $module$Yested.net.yested.core.properties.zip_p5zq0b$;
  var zip_0 = $module$Yested.net.yested.core.properties.zip_ks3sk4$;
  var DialogSize = $module$Yested.net.yested.ext.bootstrap3.DialogSize;
  var p = $module$Yested.net.yested.core.html.p_kupg11$;
  var equals = Kotlin.equals;
  var prepareDialog = $module$Yested.net.yested.ext.bootstrap3.prepareDialog_y3ee7l$;
  var repeatLive = $module$Yested.net.yested.core.html.repeatLive_8h14ne$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var mapIfNotNull = $module$Yested.net.yested.core.properties.mapIfNotNull_5zjfbp$;
  var h4 = $module$Yested.net.yested.core.html.h4_omchdl$;
  var small = $module$Yested.net.yested.core.html.small_omchdl$;
  var navTabs = $module$Yested.net.yested.ext.bootstrap3.navTabs_b2xvts$;
  var setClassPresence = $module$Yested.net.yested.core.html.setClassPresence_xnajm1$;
  var u = $module$Yested.net.yested.core.html.u_mvuu9r$;
  var btsFormDefault = $module$Yested.net.yested.ext.bootstrap3.btsFormDefault_swm8s9$;
  var removeAll = Kotlin.kotlin.collections.removeAll_qafx1e$;
  var modifyList = $module$Yested.net.yested.core.properties.modifyList_8y3skq$;
  var split = Kotlin.kotlin.text.split_o64adg$;
  var mapWith = $module$Yested.net.yested.core.properties.mapWith_fuohfe$;
  var zip_1 = $module$Yested.net.yested.core.properties.zip_fmr2eb$;
  var async = $module$Yested.net.yested.core.properties.async_ab0yle$;
  var plus = Kotlin.kotlin.collections.plus_mydzjv$;
  var distinct = Kotlin.kotlin.collections.distinct_7wnvza$;
  var flatMap = $module$Yested.net.yested.core.properties.flatMap_54eayl$;
  var text = Kotlin.kotlin.text;
  var unboxChar = Kotlin.unboxChar;
  var then = Kotlin.kotlin.comparisons.then_15rrmw$;
  var removeClass = Kotlin.kotlin.dom.removeClass_hhb33f$;
  var onChange = $module$Yested.net.yested.core.properties.onChange_bnmtqx$;
  var setClassPresence_0 = $module$Yested.net.yested.core.html.setClassPresence_bsew52$;
  var emptySet = Kotlin.kotlin.collections.emptySet_287e2$;
  var setOf = Kotlin.kotlin.collections.setOf_i5x0yv$;
  var plus_0 = Kotlin.kotlin.collections.plus_khz7k3$;
  var Pair = Kotlin.kotlin.Pair;
  var json = Kotlin.kotlin.js.json_pyyo18$;
  var RuntimeException = Kotlin.kotlin.RuntimeException;
  var throwCCE = Kotlin.throwCCE;
  var Moment = $module$Yested.net.yested.ext.moment.Moment;
  var format = $module$Yested.net.yested.ext.moment.format_l34jk1$;
  var hasClass = Kotlin.kotlin.dom.hasClass_46n0ku$;
  var removeAt = $module$Yested.net.yested.core.properties.removeAt_ujn12a$;
  var add = $module$Yested.net.yested.core.properties.add_db07b2$;
  var with_0 = $module$Yested.net.yested.core.utils.with_9bxh2u$;
  var clear = $module$Yested.net.yested.core.properties.clear_l7rwuc$;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var reversed = Kotlin.kotlin.collections.reversed_7wnvza$;
  var PropertyMetadata = Kotlin.PropertyMetadata;
  var firstOrNull = Kotlin.kotlin.collections.firstOrNull_7wnvza$;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var Any = Object;
  var toLong = Kotlin.kotlin.text.toLong_pdl1vz$;
  var Throwable = Error;
  var mapOf = Kotlin.kotlin.collections.mapOf_qfcya0$;
  var lazy = Kotlin.kotlin.lazy_klfg04$;
  var toMutableMap = Kotlin.kotlin.collections.toMutableMap_abgq59$;
  var filterNotNull = Kotlin.kotlin.collections.filterNotNull_m3lr2h$;
  var mutableMapOf = Kotlin.kotlin.collections.mutableMapOf_qfcya0$;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var toByte = Kotlin.toByte;
  var Regex = Kotlin.kotlin.text.Regex_61zpoe$;
  var Fade = $module$Yested.net.yested.ext.jquery.Fade;
  var setChild = $module$Yested.net.yested.ext.jquery.setChild_496hx5$;
  var get_splitHashProperty = $module$Yested.net.yested.ext.jquery.get_splitHashProperty_uq8nrx$;
  var initializeApp = $module$firebase.initializeApp;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var SortSpecification_init = $module$Yested.net.yested.core.utils.SortSpecification_init_sir3ds$;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var IllegalArgumentException = Kotlin.kotlin.IllegalArgumentException;
  var toList = Kotlin.kotlin.collections.toList_7wnvza$;
  var toMutableList = Kotlin.kotlin.collections.toMutableList_4c7yge$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_mqih57$;
  var setOf_0 = Kotlin.kotlin.collections.setOf_mh5how$;
  var ReadOnlyProperty = $module$Yested.net.yested.core.properties.ReadOnlyProperty;
  var firstOrNull_0 = Kotlin.kotlin.collections.firstOrNull_2p1efm$;
  var flatMapOrNull = $module$Yested.net.yested.core.properties.flatMapOrNull_8mviy2$;
  var numberToLong = Kotlin.numberToLong;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var collectAsDefault = $module$Yested.net.yested.core.properties.collectAsDefault_b3gz4f$;
  var minus_0 = Kotlin.kotlin.collections.minus_xfiyik$;
  var plus_1 = Kotlin.kotlin.collections.plus_xfiyik$;
  var modify = $module$Yested.net.yested.core.properties.modify_hmqq2r$;
  var contains = Kotlin.kotlin.collections.contains_2ws7j4$;
  var contains_0 = Kotlin.kotlin.text.contains_li3zpu$;
  var take = Kotlin.kotlin.text.take_6ic1pp$;
  var listOf_0 = Kotlin.kotlin.collections.listOf_mh5how$;
  var abs = Kotlin.kotlin.math.abs_za3lpa$;
  var toSet = Kotlin.kotlin.collections.toSet_us0mfu$;
  var first_0 = Kotlin.kotlin.collections.first_7wnvza$;
  var sum = Kotlin.kotlin.collections.sum_lvsncp$;
  var sum_0 = Kotlin.kotlin.collections.sum_plj8ka$;
  var mutableSetOf = Kotlin.kotlin.collections.mutableSetOf_i5x0yv$;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var toMutableSet = Kotlin.kotlin.collections.toMutableSet_7wnvza$;
  var hashCode = Kotlin.hashCode;
  var properties = Kotlin.kotlin.properties;
  var Comparable = Kotlin.kotlin.Comparable;
  var UnsupportedOperationException = Kotlin.kotlin.UnsupportedOperationException;
  var asSequence = Kotlin.kotlin.collections.asSequence_7wnvza$;
  var map_0 = Kotlin.kotlin.sequences.map_z5avom$;
  var toSet_0 = Kotlin.kotlin.collections.toSet_7wnvza$;
  TagValueModel.prototype = Object.create(TagModel.prototype);
  TagValueModel.prototype.constructor = TagValueModel;
  OptionTagModel.prototype = Object.create(TagValueModel.prototype);
  OptionTagModel.prototype.constructor = OptionTagModel;
  TagCriteriaModel.prototype = Object.create(TagModel.prototype);
  TagCriteriaModel.prototype.constructor = TagCriteriaModel;
  TagTagModel.prototype = Object.create(TagValueModel.prototype);
  TagTagModel.prototype.constructor = TagTagModel;
  TagValueForOptionLabelModel.prototype = Object.create(TagValueModel.prototype);
  TagValueForOptionLabelModel.prototype.constructor = TagValueForOptionLabelModel;
  UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral.prototype = Object.create(Command.prototype);
  UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral.prototype.constructor = UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral;
  UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral.prototype = Object.create(Command.prototype);
  UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral.prototype.constructor = UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral;
  UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral.prototype = Object.create(Command.prototype);
  UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral.prototype.constructor = UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral;
  UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral.prototype = Object.create(Command.prototype);
  UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral.prototype.constructor = UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral;
  UndoableGroup$executeAndGetOpposite$ObjectLiteral.prototype = Object.create(Command.prototype);
  UndoableGroup$executeAndGetOpposite$ObjectLiteral.prototype.constructor = UndoableGroup$executeAndGetOpposite$ObjectLiteral;
  UndoableGroup.prototype = Object.create(Command.prototype);
  UndoableGroup.prototype.constructor = UndoableGroup;
  ProtectionLevel.prototype = Object.create(Enum.prototype);
  ProtectionLevel.prototype.constructor = ProtectionLevel;
  function buttonBar$lambda$lambda$lambda$lambda$lambda(closure$backHash) {
    return function (f) {
      backToHash(window.history, closure$backHash.get());
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      set_visible(this$, it != null);
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda$lambda_0(closure$backHash) {
    return function ($receiver) {
      addClass($receiver, ['nowrap']);
      closure$backHash.onNext_qlkmfe$(buttonBar$lambda$lambda$lambda$lambda$lambda$lambda($receiver));
      flaticon($receiver, 'arrow-pointing-to-left-1');
      appendText($receiver, 'Back');
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda(closure$backHash) {
    return function ($receiver) {
      var tmp$;
      $receiver.id = 'backButton';
      tmp$ = ButtonSize.Default;
      btsButton($receiver, ButtonLook.Default, tmp$, void 0, buttonBar$lambda$lambda$lambda$lambda$lambda(closure$backHash), void 0, void 0, buttonBar$lambda$lambda$lambda$lambda$lambda_0(closure$backHash));
      nbsp($receiver, 2);
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      set_visible(this$, it != null);
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda$lambda$lambda_0(this$, closure$headingHref) {
    return function (it) {
      this$.href = it != null ? it : '';
      closure$headingHref.onNext_qlkmfe$(buttonBar$lambda$lambda$lambda$lambda$lambda$lambda$lambda(this$));
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda$lambda$lambda_1(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda$lambda_1(closure$headingHref, closure$heading) {
    return function ($receiver) {
      closure$headingHref.onNext_qlkmfe$(buttonBar$lambda$lambda$lambda$lambda$lambda$lambda_0($receiver, closure$headingHref));
      closure$heading.onNext_qlkmfe$(buttonBar$lambda$lambda$lambda$lambda$lambda$lambda_1($receiver));
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda$lambda$lambda_2(this$) {
    return function (it) {
      set_visible(this$, it == null);
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda$lambda$lambda_3(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda$lambda_2(closure$headingHref, closure$heading) {
    return function ($receiver) {
      closure$headingHref.onNext_qlkmfe$(buttonBar$lambda$lambda$lambda$lambda$lambda$lambda_2($receiver));
      closure$heading.onNext_qlkmfe$(buttonBar$lambda$lambda$lambda$lambda$lambda$lambda_3($receiver));
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda$lambda_0(closure$headingHref, closure$heading) {
    return function ($receiver) {
      addClass($receiver, ['nowrap']);
      a($receiver, buttonBar$lambda$lambda$lambda$lambda$lambda_1(closure$headingHref, closure$heading));
      span($receiver, buttonBar$lambda$lambda$lambda$lambda$lambda_2(closure$headingHref, closure$heading));
      return Unit;
    };
  }
  function buttonBar$lambda$lambda$lambda(closure$backHash, closure$headingHref, closure$heading) {
    return function ($receiver) {
      span($receiver, buttonBar$lambda$lambda$lambda$lambda(closure$backHash));
      h3($receiver, buttonBar$lambda$lambda$lambda$lambda_0(closure$headingHref, closure$heading));
      return Unit;
    };
  }
  function buttonBar$lambda$lambda(closure$backHash, closure$headingHref, closure$heading) {
    return function ($receiver) {
      col($receiver, and(new Col$Width$Tn(12), new Col$Width$Xs(12)), buttonBar$lambda$lambda$lambda(closure$backHash, closure$headingHref, closure$heading));
      return Unit;
    };
  }
  function buttonBar$lambda(closure$backHash, closure$headingHref, closure$heading) {
    return function ($receiver) {
      $receiver.navbarContainer.id = 'buttonBar';
      row($receiver.navbarContainer, buttonBar$lambda$lambda(closure$backHash, closure$headingHref, closure$heading));
      return Unit;
    };
  }
  function buttonBar($receiver, backHash, heading, headingHref) {
    if (backHash === void 0)
      backHash = toProperty(null);
    if (heading === void 0)
      heading = toProperty(null);
    if (headingHref === void 0)
      headingHref = toProperty(null);
    navbar($receiver, NavbarCompletePosition.FixedTop, void 0, ContainerWidth.Fluid, buttonBar$lambda(backHash, headingHref, heading));
    br($receiver);
    br($receiver);
    br($receiver);
  }
  function DataSetModel(dataSetId, dataSetRepository) {
    DataSetModel$Companion_getInstance();
    if (dataSetRepository === void 0)
      dataSetRepository = Factory_getInstance().dataSetRepository;
    this.dataSetId = dataSetId;
    this.dataSetRepository = dataSetRepository;
    this.dataSet = flatMapIfNotNull(this.dataSetId, DataSetModel$dataSet$lambda(this));
    this.label = mapAsDefault(this.dataSet, DataSetModel$label$lambda);
    this.quickSummary = mapAsDefault(this.dataSet, DataSetModel$quickSummary$lambda);
    this.validation = validate(this.label, 'Label is mandatory', DataSetModel$validation$lambda);
    this.backHash = toProperty(DataSetsModel$Companion_getInstance().toUrl());
  }
  DataSetModel.prototype.save = function () {
    var tmp$;
    if (this.validation.get().success) {
      var updatedDataSet = new DataSet(this.label.get(), this.quickSummary.get(), void 0, (tmp$ = this.dataSet.get()) != null ? tmp$.id : null);
      var newId = this.dataSetRepository.save_tf6uce$(this.dataSet.get(), updatedDataSet);
      this.dataSetId.set_11rb$(newId);
      backToHash(window.history, this.backHash.get());
      return true;
    }
     else {
      return false;
    }
  };
  DataSetModel.prototype.cancel = function () {
    this.dataSetId.set_11rb$(null);
    backToHash(window.history, this.backHash.get());
  };
  DataSetModel.prototype.delete = function () {
    var tmp$;
    if ((tmp$ = this.dataSet.get()) != null) {
      this.dataSetRepository.remove_g5e3t3$(tmp$);
    }
    backToHash(window.history, DataSetsModel$Companion_getInstance().toUrl());
  };
  function DataSetModel$Companion() {
    DataSetModel$Companion_instance = this;
  }
  DataSetModel$Companion.prototype.toUrl_5mhfxq$ = function (dataSetId) {
    return '#dataSet' + (dataSetId != null ? '/' + toString(dataSetId) : '');
  };
  DataSetModel$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DataSetModel$Companion_instance = null;
  function DataSetModel$Companion_getInstance() {
    if (DataSetModel$Companion_instance === null) {
      new DataSetModel$Companion();
    }
    return DataSetModel$Companion_instance;
  }
  function DataSetModel$dataSet$lambda(this$DataSetModel) {
    return function (it) {
      return findProperty(this$DataSetModel.dataSetRepository, it);
    };
  }
  function DataSetModel$label$lambda(it) {
    var tmp$;
    return (tmp$ = it != null ? it.label : null) != null ? tmp$ : '';
  }
  function DataSetModel$quickSummary$lambda(it) {
    var tmp$;
    return (tmp$ = it != null ? it.quickSummary : null) != null ? tmp$ : '';
  }
  function DataSetModel$validation$lambda(it) {
    return it.length > 0;
  }
  DataSetModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DataSetModel',
    interfaces: []
  };
  function dataSetScreen$lambda$lambda(closure$model, this$) {
    return function () {
      buttonBar(this$, closure$model.backHash, toProperty('Data Set'));
      return Unit;
    };
  }
  function dataSetScreen$lambda$lambda$lambda(it) {
    return toState(it);
  }
  function dataSetScreen$lambda$lambda$lambda$lambda($receiver) {
    $receiver.placeholder = 'Label';
    $receiver.size = 30;
    return Unit;
  }
  function dataSetScreen$lambda$lambda$lambda_0(closure$model) {
    return function ($receiver, it) {
      textInput($receiver, closure$model.label, void 0, void 0, void 0, void 0, dataSetScreen$lambda$lambda$lambda$lambda);
      return Unit;
    };
  }
  function dataSetScreen$lambda$lambda$lambda$lambda_0($receiver) {
    $receiver.placeholder = 'Quick Summary';
    $receiver.size = 80;
    return Unit;
  }
  function dataSetScreen$lambda$lambda$lambda_1(closure$model) {
    return function ($receiver, it) {
      textInput($receiver, closure$model.quickSummary, void 0, void 0, void 0, void 0, dataSetScreen$lambda$lambda$lambda$lambda_0);
      return Unit;
    };
  }
  function dataSetScreen$lambda$lambda$lambda$lambda_1(closure$model) {
    return function (it) {
      closure$model.save();
      return Unit;
    };
  }
  function dataSetScreen$lambda$lambda$lambda$lambda_2($receiver) {
    appendText($receiver, 'Save');
    return Unit;
  }
  function dataSetScreen$lambda$lambda$lambda$lambda_3(closure$model) {
    return function (it) {
      closure$model.cancel();
      return Unit;
    };
  }
  function dataSetScreen$lambda$lambda$lambda$lambda_4($receiver) {
    appendText($receiver, 'Cancel');
    return Unit;
  }
  function dataSetScreen$lambda$lambda$lambda$lambda_5(closure$model) {
    return function (it) {
      closure$model.delete();
      return Unit;
    };
  }
  function dataSetScreen$lambda$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      set_visible(this$, (it != null ? it.id : null) != null);
      return Unit;
    };
  }
  function dataSetScreen$lambda$lambda$lambda$lambda_6(closure$model) {
    return function ($receiver) {
      closure$model.dataSet.onNext_qlkmfe$(dataSetScreen$lambda$lambda$lambda$lambda$lambda($receiver));
      appendText($receiver, 'Delete Data Set');
      return Unit;
    };
  }
  function dataSetScreen$lambda$lambda$lambda_2(closure$model) {
    return function ($receiver, it) {
      var tmp$;
      addClass($receiver, ['btn-toolbar']);
      btsButton($receiver, ButtonLook.Primary, void 0, void 0, dataSetScreen$lambda$lambda$lambda$lambda_1(closure$model), void 0, void 0, dataSetScreen$lambda$lambda$lambda$lambda_2);
      btsButton($receiver, void 0, void 0, void 0, dataSetScreen$lambda$lambda$lambda$lambda_3(closure$model), void 0, void 0, dataSetScreen$lambda$lambda$lambda$lambda_4);
      nbsp($receiver, 4);
      tmp$ = dataSetScreen$lambda$lambda$lambda$lambda_5(closure$model);
      btsButton($receiver, ButtonLook.Danger, void 0, void 0, tmp$, void 0, void 0, dataSetScreen$lambda$lambda$lambda$lambda_6(closure$model));
      return Unit;
    };
  }
  function dataSetScreen$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      $receiver.btsFormItemSimple_ekuevp$(void 0, map(closure$model.validation, dataSetScreen$lambda$lambda$lambda), 'Label', dataSetScreen$lambda$lambda$lambda_0(closure$model));
      $receiver.btsFormItemSimple_ekuevp$(void 0, new Property(State.Companion.Default), 'Quick Summary', dataSetScreen$lambda$lambda$lambda_1(closure$model));
      $receiver.btsFormItemSimple_ekuevp$(void 0, new Property(State.Companion.Default), '', dataSetScreen$lambda$lambda$lambda_2(closure$model));
      return Unit;
    };
  }
  function dataSetScreen$lambda(closure$model) {
    return function ($receiver) {
      inContext('buttonBar', dataSetScreen$lambda$lambda(closure$model, $receiver));
      btsFormHorizontal($receiver, new Col$Width$Sm(4), new Col$Width$Sm(8), dataSetScreen$lambda$lambda_0(closure$model));
      return Unit;
    };
  }
  function dataSetScreen(model) {
    return Div(dataSetScreen$lambda(model));
  }
  function DataSetsModel(repository, optionRepository, optionTagRepository, tagRepository) {
    DataSetsModel$Companion_getInstance();
    if (repository === void 0)
      repository = Factory_getInstance().dataSetRepository;
    if (optionRepository === void 0)
      optionRepository = Factory_getInstance().optionRepository;
    if (optionTagRepository === void 0)
      optionTagRepository = Factory_getInstance().optionTagRepository;
    if (tagRepository === void 0)
      tagRepository = Factory_getInstance().tagRepository;
    this.repository = repository;
    this.optionRepository = optionRepository;
    this.optionTagRepository = optionTagRepository;
    this.tagRepository = tagRepository;
    this.dataIds = idListProperty(this.repository);
    this.dataProperties = map(this.dataIds, DataSetsModel$dataProperties$lambda(this));
    this.currentSort = new Property(null);
  }
  function DataSetsModel$delete$lambda$lambda(this$DataSetsModel, closure$dataSet, closure$dataSetId) {
    return function () {
      this$DataSetsModel.repository.remove_g5e3t3$(closure$dataSet);
      var $receiver = this$DataSetsModel.optionTagRepository.list_fmyu0x$(new OptionTagByDataSetId(closure$dataSetId));
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        this$DataSetsModel.optionTagRepository.remove_g5e3t3$(element);
      }
      var $receiver_0 = this$DataSetsModel.tagRepository.list_fmyu0x$(new TagByDataSetId(closure$dataSetId));
      var tmp$_0;
      tmp$_0 = $receiver_0.iterator();
      while (tmp$_0.hasNext()) {
        var element_0 = tmp$_0.next();
        this$DataSetsModel.tagRepository.remove_g5e3t3$(element_0);
      }
      var $receiver_1 = this$DataSetsModel.optionRepository.list_fmyu0x$(new OptionByDataSetId(closure$dataSetId));
      var tmp$_1;
      tmp$_1 = $receiver_1.iterator();
      while (tmp$_1.hasNext()) {
        var element_1 = tmp$_1.next();
        this$DataSetsModel.optionRepository.remove_g5e3t3$(element_1);
      }
      return Unit;
    };
  }
  DataSetsModel.prototype.delete_wc5hdh$ = function (dataSet) {
    var tmp$;
    if ((tmp$ = dataSet.id) != null) {
      UndoComponent_getInstance().undoable_u8di54$('Delete Data Set', 'Restore Data Set', DataSetsModel$delete$lambda$lambda(this, dataSet, tmp$));
    }
  };
  function DataSetsModel$Companion() {
    DataSetsModel$Companion_instance = this;
  }
  DataSetsModel$Companion.prototype.toUrl = function () {
    return '#dataSets';
  };
  DataSetsModel$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DataSetsModel$Companion_instance = null;
  function DataSetsModel$Companion_getInstance() {
    if (DataSetsModel$Companion_instance === null) {
      new DataSetsModel$Companion();
    }
    return DataSetsModel$Companion_instance;
  }
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  function DataSetsModel$dataProperties$lambda(this$DataSetsModel) {
    return function (it) {
      var destination = ArrayList_init(collectionSizeOrDefault(it, 10));
      var tmp$;
      tmp$ = it.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(findProperty(this$DataSetsModel.repository, item));
      }
      return destination;
    };
  }
  DataSetsModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DataSetsModel',
    interfaces: []
  };
  function dataSetsScreen$lambda$lambda(this$) {
    return function () {
      buttonBar(this$, toProperty(null), toProperty('Data Sets'));
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda(it) {
    return it != null ? it.label : null;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0($receiver) {
    appendText($receiver, 'DataSets');
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      $receiver.className = 'text-left';
      sortControlWithArrow($receiver, closure$model.currentSort, compareByProperty(dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda), void 0, true, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0);
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1(it) {
    return it != null ? it.quickSummary : null;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda($receiver) {
    appendText($receiver, 'Quick Summary');
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_2($receiver) {
    div($receiver, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda);
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      $receiver.className = 'text-left';
      sortControlWithArrow($receiver, closure$model.currentSort, compareByProperty(dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1), void 0, void 0, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_2);
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      th($receiver, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda(closure$model));
      th($receiver, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$model));
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      addClass($receiver, ['hidden-tn hidden-xxs']);
      tr($receiver, void 0, void 0, dataSetsScreen$lambda$lambda$lambda$lambda$lambda(closure$model));
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      this$.textContent = it != null ? it.label : null;
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0($receiver, it) {
    it.onNext_qlkmfe$(dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda($receiver));
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_3(closure$dataSet) {
    return function ($receiver) {
      $receiver.className = 'text-left name';
      showOptionsOnClick($receiver, closure$dataSet, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0);
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$dataSet) {
    return function ($receiver) {
      div($receiver, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_3(closure$dataSet));
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(this$) {
    return function (it) {
      var tmp$;
      this$.textContent = (tmp$ = it != null ? it.quickSummary : null) != null ? tmp$ : '';
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1($receiver, it) {
    it.onNext_qlkmfe$(dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0($receiver));
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_4(closure$dataSet) {
    return function ($receiver) {
      $receiver.className = 'text-left';
      showOptionsOnClick($receiver, closure$dataSet, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1);
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$dataSet) {
    return function ($receiver) {
      div($receiver, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_4(closure$dataSet));
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda_0(closure$dataSet) {
    return function ($receiver) {
      td($receiver, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$dataSet));
      td($receiver, dataSetsScreen$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$dataSet));
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda_0($receiver, dataSet) {
    tr($receiver, void 0, void 0, dataSetsScreen$lambda$lambda$lambda$lambda$lambda_0(dataSet));
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda(closure$model, closure$animate) {
    return function ($receiver) {
      $receiver.className = 'table table-striped table-hover table-condensed';
      thead($receiver, dataSetsScreen$lambda$lambda$lambda$lambda(closure$model));
      tbody($receiver, sortedWith(closure$model.dataProperties, closure$model.currentSort), closure$animate ? new Collapse() : utils.NoEffect, dataSetsScreen$lambda$lambda$lambda$lambda_0);
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda_0(closure$model, closure$animate) {
    return function ($receiver) {
      addClass($receiver, ['container-fluid']);
      $receiver.className = 'table-responsive';
      table($receiver, dataSetsScreen$lambda$lambda$lambda(closure$model, closure$animate));
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda_1(this$) {
    return function (it) {
      set_visible(this$, it.isEmpty());
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      closure$model.dataIds.onNext_qlkmfe$(dataSetsScreen$lambda$lambda$lambda$lambda_1($receiver));
      appendText($receiver, 'There are no data sets.');
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda_1(closure$model) {
    return function ($receiver) {
      col($receiver, new Col$Width$Xs(12), dataSetsScreen$lambda$lambda$lambda_0(closure$model));
      return Unit;
    };
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda_2($receiver) {
    undoComponent($receiver);
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda_1(it) {
    window.location.hash = DataSetModel$Companion_getInstance().toUrl_5mhfxq$(null);
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda$lambda_2($receiver) {
    appendText($receiver, 'Add');
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda$lambda_3($receiver) {
    addClass($receiver, ['text-right']);
    FileBackupComponent_getInstance().backupButton_y4uc6z$($receiver);
    btsButton($receiver, void 0, void 0, void 0, dataSetsScreen$lambda$lambda$lambda$lambda$lambda_1, void 0, void 0, dataSetsScreen$lambda$lambda$lambda$lambda$lambda_2);
    return Unit;
  }
  function dataSetsScreen$lambda$lambda$lambda_1($receiver) {
    col($receiver, and(new Col$Width$Tn(4), new Col$Width$Xs(4)), dataSetsScreen$lambda$lambda$lambda$lambda_2);
    col($receiver, and(new Col$Width$Tn(8), new Col$Width$Xs(8)), dataSetsScreen$lambda$lambda$lambda$lambda_3);
    return Unit;
  }
  function dataSetsScreen$lambda$lambda_2($receiver) {
    row($receiver.navbarContainer, dataSetsScreen$lambda$lambda$lambda_1);
    return Unit;
  }
  function dataSetsScreen$lambda(closure$model, closure$animate) {
    return function ($receiver) {
      inContext('buttonBar', dataSetsScreen$lambda$lambda($receiver));
      div($receiver, dataSetsScreen$lambda$lambda_0(closure$model, closure$animate));
      row($receiver, dataSetsScreen$lambda$lambda_1(closure$model));
      br($receiver);
      br($receiver);
      br($receiver);
      br($receiver);
      navbar($receiver, NavbarCompletePosition.FixedBottom, void 0, ContainerWidth.Fluid, dataSetsScreen$lambda$lambda_2);
      return Unit;
    };
  }
  function dataSetsScreen(model, animate) {
    if (animate === void 0)
      animate = true;
    return Div(dataSetsScreen$lambda(model, animate));
  }
  function showOptionsOnClick$lambda$lambda(this$) {
    return function (it) {
      this$.href = OptionsModel$Companion_getInstance().toUrl_5mhfxq$(it != null ? it.id : null);
      return Unit;
    };
  }
  function showOptionsOnClick$lambda(closure$dataSet, closure$render) {
    return function ($receiver) {
      closure$dataSet.onNext_qlkmfe$(showOptionsOnClick$lambda$lambda($receiver));
      closure$render($receiver, closure$dataSet);
      return Unit;
    };
  }
  function showOptionsOnClick($receiver, dataSet, render) {
    a($receiver, showOptionsOnClick$lambda(dataSet, render));
  }
  function OptionModel(optionId, dataSetId, tagCriteriaList, alphabeticalTagIds, optionTagRepository, tagRepository) {
    OptionModel$Companion_getInstance();
    if (optionTagRepository === void 0)
      optionTagRepository = Factory_getInstance().optionTagRepository;
    if (tagRepository === void 0)
      tagRepository = Factory_getInstance().tagRepository;
    this.optionId = optionId;
    this.dataSetId = dataSetId;
    this.optionTagRepository = optionTagRepository;
    this.tagRepository = tagRepository;
    this.option = mapAsDefault(this.optionId, OptionModel$option$lambda(this));
    this.optionRepository = Factory_getInstance().optionRepository;
    this.label = mapAsDefault(this.option, OptionModel$label$lambda);
    this.quickSummary = mapAsDefault(this.option, OptionModel$quickSummary$lambda);
    this.url = mapAsDefault(this.option, OptionModel$url$lambda);
    this.validation = validate(this.label, 'Label is mandatory', OptionModel$validation$lambda);
    this.persistedTagValues_0 = flatMapIfNotNull(this.optionId, OptionModel$persistedTagValues$lambda(this));
    this.tagValues = mapAsDefault(map(zip(this.persistedTagValues_0, tagCriteriaList), OptionModel$tagValues$lambda), OptionModel$tagValues$lambda_0);
    this.isExpanded = toProperty(false);
    this.tagIdsToShow = map(zip_0(this.isExpanded, this.tagValues, alphabeticalTagIds), OptionModel$tagIdsToShow$lambda);
    this.backHash = map(this.dataSetId, OptionModel$backHash$lambda);
  }
  OptionModel.prototype.toggleExpanded = function () {
    this.isExpanded.set_11rb$(!this.isExpanded.get());
  };
  function OptionModel$save$lambda(this$OptionModel, closure$updatedOption, closure$dataSetId) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      var newId = this$OptionModel.optionRepository.save_g5e3t3$(closure$updatedOption);
      var tmp$_3;
      if ((tmp$ = this$OptionModel.persistedTagValues_0.get()) != null) {
        var destination = ArrayList_init(collectionSizeOrDefault(tmp$, 10));
        var tmp$_4;
        tmp$_4 = tmp$.iterator();
        while (tmp$_4.hasNext()) {
          var item = tmp$_4.next();
          destination.add_11rb$(item.tagId);
        }
        tmp$_3 = destination;
      }
       else
        tmp$_3 = null;
      var tmp$_5;
      if ((tmp$_0 = tmp$_3) != null) {
        var $receiver = this$OptionModel.tagValues.get();
        var destination_0 = ArrayList_init(collectionSizeOrDefault($receiver, 10));
        var tmp$_6;
        tmp$_6 = $receiver.iterator();
        while (tmp$_6.hasNext()) {
          var item_0 = tmp$_6.next();
          destination_0.add_11rb$(item_0.tagId);
        }
        tmp$_5 = minus(tmp$_0, destination_0);
      }
       else
        tmp$_5 = null;
      if ((tmp$_1 = tmp$_5) != null) {
        var tmp$_7;
        tmp$_7 = tmp$_1.iterator();
        while (tmp$_7.hasNext()) {
          var element = tmp$_7.next();
          remove(this$OptionModel.optionTagRepository, new OptionTagIdPair(newId, element));
        }
      }
      var $receiver_0 = minus(this$OptionModel.tagValues.get(), (tmp$_2 = this$OptionModel.persistedTagValues_0.get()) != null ? tmp$_2 : emptyList());
      var tmp$_8;
      tmp$_8 = $receiver_0.iterator();
      while (tmp$_8.hasNext()) {
        var element_0 = tmp$_8.next();
        setPercentage_0(this$OptionModel.optionTagRepository, new OptionTag(newId, element_0.tagId, element_0.percentage, closure$dataSetId));
      }
      this$OptionModel.optionId.set_11rb$(newId);
      return Unit;
    };
  }
  OptionModel.prototype.save_6taknv$ = function (goBack) {
    if (goBack === void 0)
      goBack = true;
    var dataSetId = this.dataSetId.get();
    if (this.validation.get().success && dataSetId != null) {
      var optionToSave = this.option.get();
      var updatedOption = new Option(this.label.get(), dataSetId, this.quickSummary.get(), emptyToNull(this.url.get()), optionToSave != null ? optionToSave.id : null);
      UndoComponent_getInstance().undoable_u8di54$('Saved ' + this.option, 'Reverted ' + this.option, OptionModel$save$lambda(this, updatedOption, dataSetId));
      if (goBack) {
        backToHash(window.history, this.backHash.get());
      }
       else {
        this.optionId.set_11rb$(null);
      }
      return true;
    }
     else {
      return false;
    }
  };
  OptionModel.prototype.cancel = function () {
    backToHash(window.history, this.backHash.get());
  };
  OptionModel.prototype.delete = function () {
    var tmp$;
    if ((tmp$ = this.option.get()) != null) {
      this.optionRepository.remove_g5e3t3$(tmp$);
    }
    backToHash(window.history, OptionsModel$Companion_getInstance().toUrl_5mhfxq$(this.dataSetId.get()));
  };
  var isBlank = Kotlin.kotlin.text.isBlank_gw00vp$;
  OptionModel.prototype.saveNewTag_pri9vf$ = function (newTagLabel, htmlInputElement, event, newPercentage) {
    if (newPercentage === void 0)
      newPercentage = 100;
    var newLabel = newTagLabel.get();
    var dataSetIdValue = this.dataSetId.get();
    if (!isBlank(newLabel) && dataSetIdValue != null) {
      var newTagId = findOrCreate(this.tagRepository, newLabel, dataSetIdValue);
      setPercentage(this.tagValues, newTagId, newPercentage);
      event.preventDefault();
      htmlInputElement.focus();
    }
    newTagLabel.set_11rb$('');
  };
  function OptionModel$Companion() {
    OptionModel$Companion_instance = this;
  }
  OptionModel$Companion.prototype.toUrl_j97ou7$ = function (optionId) {
    return '#option' + (optionId != null ? '/' + toString(optionId) : '');
  };
  OptionModel$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var OptionModel$Companion_instance = null;
  function OptionModel$Companion_getInstance() {
    if (OptionModel$Companion_instance === null) {
      new OptionModel$Companion();
    }
    return OptionModel$Companion_instance;
  }
  function OptionModel$option$lambda(this$OptionModel) {
    return function (it) {
      var tmp$;
      tmp$ = it != null ? this$OptionModel.optionRepository.find_83a66n$(it) : null;
      return tmp$;
    };
  }
  function OptionModel$label$lambda(it) {
    var tmp$;
    return (tmp$ = it != null ? it.label : null) != null ? tmp$ : '';
  }
  function OptionModel$quickSummary$lambda(it) {
    var tmp$;
    return (tmp$ = it != null ? it.quickSummary : null) != null ? tmp$ : '';
  }
  function OptionModel$url$lambda(it) {
    var tmp$;
    return (tmp$ = it != null ? it.url : null) != null ? tmp$ : '';
  }
  function OptionModel$validation$lambda(it) {
    return it.length > 0;
  }
  function OptionModel$persistedTagValues$lambda(this$OptionModel) {
    return function (it) {
      return listProperty_0(this$OptionModel.optionTagRepository, OptionTagValueField_getInstance(), new OptionTagByOptionId(it));
    };
  }
  function OptionModel$tagValues$lambda(f) {
    var persistedTagValues = f.component1()
    , tagCriteriaList = f.component2();
    var tmp$;
    if (persistedTagValues != null)
      tmp$ = persistedTagValues;
    else {
      var destination = ArrayList_init(collectionSizeOrDefault(tagCriteriaList, 10));
      var tmp$_0;
      tmp$_0 = tagCriteriaList.iterator();
      while (tmp$_0.hasNext()) {
        var item = tmp$_0.next();
        destination.add_11rb$(item.tagValue);
      }
      tmp$ = destination;
    }
    return tmp$;
  }
  function OptionModel$tagValues$lambda_0(it) {
    return it;
  }
  function OptionModel$tagIdsToShow$lambda(f) {
    var expanded = f.component1()
    , tagValues = f.component2()
    , alphabeticalTagIds = f.component3();
    var tmp$;
    if (expanded)
      tmp$ = alphabeticalTagIds;
    else {
      var destination = ArrayList_init(collectionSizeOrDefault(tagValues, 10));
      var tmp$_0;
      tmp$_0 = tagValues.iterator();
      while (tmp$_0.hasNext()) {
        var item = tmp$_0.next();
        destination.add_11rb$(item.tagId);
      }
      tmp$ = destination;
    }
    return tmp$;
  }
  function OptionModel$backHash$lambda(it) {
    return OptionsModel$Companion_getInstance().toUrl_5mhfxq$(it);
  }
  OptionModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionModel',
    interfaces: []
  };
  function OptionModel_init(optionId, dataSetId, optionsModel, $this) {
    $this = $this || Object.create(OptionModel.prototype);
    OptionModel.call($this, optionId, dataSetId, optionsModel.tagCriteriaList, optionsModel.alphabeticalTagIds);
    return $this;
  }
  function optionDialog$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      closure$model.label.onNext_qlkmfe$(optionDialog$lambda$lambda$lambda$lambda($receiver));
      return Unit;
    };
  }
  function optionDialog$lambda$lambda(closure$model) {
    return function ($receiver) {
      h3($receiver, optionDialog$lambda$lambda$lambda(closure$model));
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda$lambda_0(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      closure$model.quickSummary.onNext_qlkmfe$(optionDialog$lambda$lambda$lambda$lambda_0($receiver));
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      this$.href = it;
      set_visible(this$, !equals(it, ''));
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda$lambda_1(closure$model) {
    return function ($receiver) {
      addClass($receiver, ['btn btn-primary btn-default']);
      closure$model.url.onNext_qlkmfe$(optionDialog$lambda$lambda$lambda$lambda$lambda($receiver));
      $receiver.textContent = 'Details';
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda_1(closure$model) {
    return function ($receiver) {
      a($receiver, optionDialog$lambda$lambda$lambda$lambda_1(closure$model));
      return Unit;
    };
  }
  function optionDialog$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      p($receiver, optionDialog$lambda$lambda$lambda_0(closure$model));
      p($receiver, optionDialog$lambda$lambda$lambda_1(closure$model));
      br($receiver);
      br($receiver);
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda$lambda$lambda_0(closure$dialog, closure$model) {
    return function (it) {
      closure$dialog.hideDialog();
      window.location.hash = OptionModel$Companion_getInstance().toUrl_j97ou7$(closure$model.optionId.get());
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda$lambda$lambda_1($receiver) {
    appendText($receiver, 'Edit');
    return Unit;
  }
  function optionDialog$lambda$lambda$lambda$lambda$lambda_2(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda$lambda$lambda_3($receiver) {
    appendText($receiver, 'Close');
    return Unit;
  }
  function optionDialog$lambda$lambda$lambda$lambda_2(closure$dialog, closure$model) {
    return function ($receiver) {
      addClass($receiver, ['text-right']);
      btsButton($receiver, void 0, void 0, void 0, optionDialog$lambda$lambda$lambda$lambda$lambda_0(closure$dialog, closure$model), void 0, void 0, optionDialog$lambda$lambda$lambda$lambda$lambda_1);
      btsButton($receiver, void 0, void 0, void 0, optionDialog$lambda$lambda$lambda$lambda$lambda_2(closure$dialog), void 0, void 0, optionDialog$lambda$lambda$lambda$lambda$lambda_3);
      return Unit;
    };
  }
  function optionDialog$lambda$lambda$lambda_2(closure$dialog, closure$model) {
    return function ($receiver) {
      col($receiver, and(new Col$Width$Tn(12), new Col$Width$Xs(12)), optionDialog$lambda$lambda$lambda$lambda_2(closure$dialog, closure$model));
      return Unit;
    };
  }
  function optionDialog$lambda$lambda_1(closure$dialog, closure$model) {
    return function ($receiver) {
      row($receiver, optionDialog$lambda$lambda$lambda_2(closure$dialog, closure$model));
      return Unit;
    };
  }
  function optionDialog$lambda(closure$model) {
    return function ($receiver, dialog) {
      $receiver.header_htzy0l$(optionDialog$lambda$lambda(closure$model));
      $receiver.body_iw61es$(optionDialog$lambda$lambda_0(closure$model));
      $receiver.footer_iw61es$(optionDialog$lambda$lambda_1(dialog, closure$model));
      return Unit;
    };
  }
  function optionDialog(model) {
    return prepareDialog(DialogSize.Default, optionDialog$lambda(model));
  }
  function optionEntryScreen$lambda$lambda(closure$model, this$) {
    return function () {
      buttonBar(this$, closure$model.backHash, toProperty('Option'));
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda(it) {
    return toState(it);
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda($receiver) {
    $receiver.placeholder = 'Label';
    $receiver.size = 30;
    return Unit;
  }
  function optionEntryScreen$lambda$lambda$lambda_0(closure$model, closure$labelInput) {
    return function ($receiver, it) {
      closure$labelInput.v = textInput($receiver, closure$model.label, void 0, void 0, void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda);
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_0($receiver) {
    $receiver.placeholder = 'Quick Summary';
    $receiver.size = 80;
    return Unit;
  }
  function optionEntryScreen$lambda$lambda$lambda_1(closure$model) {
    return function ($receiver, it) {
      textInput($receiver, closure$model.quickSummary, void 0, void 0, void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_0);
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_1($receiver) {
    $receiver.placeholder = 'Details URL';
    $receiver.size = 80;
    return Unit;
  }
  function optionEntryScreen$lambda$lambda$lambda_2(closure$model) {
    return function ($receiver, it) {
      textInput($receiver, closure$model.url, void 0, void 0, void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_1);
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda$lambda(closure$tagId) {
    return function (it) {
      UI_getInstance().showTagValueForOptionLabelDialog_apv9qp$(closure$tagId);
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_2(closure$model) {
    return function ($receiver, tagId) {
      buttonForTagValue($receiver, findProperty(Factory_getInstance().tagRepository, tagId), getPercentage(closure$model.tagValues, tagId), optionEntryScreen$lambda$lambda$lambda$lambda$lambda(tagId));
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_3(closure$model, closure$input) {
    return function (it) {
      closure$model.toggleExpanded();
      ensureNotNull(closure$input.v).focus();
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda$lambda_0(this$) {
    return function (it) {
      this$.textContent = it !== false ? '<<' : '...';
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_4(closure$model) {
    return function ($receiver) {
      closure$model.isExpanded.onNext_qlkmfe$(optionEntryScreen$lambda$lambda$lambda$lambda$lambda_0($receiver));
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda$lambda_1(closure$model, closure$newTagLabel, this$) {
    return function (event) {
      closure$model.saveNewTag_pri9vf$(closure$newTagLabel, this$, event);
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_5(closure$model, closure$newTagLabel) {
    return function ($receiver) {
      addClass($receiver, ['form-control']);
      $receiver.placeholder = 'New Tag';
      $receiver.size = 15;
      $receiver.onblur = optionEntryScreen$lambda$lambda$lambda$lambda$lambda_1(closure$model, closure$newTagLabel, $receiver);
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda_3(closure$model) {
    return function ($receiver, it) {
      repeatLive($receiver, closure$model.tagIdsToShow, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_2(closure$model));
      var input = {v: null};
      btsButton($receiver, void 0, void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_3(closure$model, input), void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_4(closure$model));
      var newTagLabel = new Property('');
      input.v = textInput($receiver, newTagLabel, void 0, void 0, void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_5(closure$model, newTagLabel));
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_6(closure$model) {
    return function (it) {
      closure$model.save_6taknv$();
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_7($receiver) {
    appendText($receiver, 'Save');
    return Unit;
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_8(closure$model, closure$labelInput) {
    return function (it) {
      var tmp$;
      closure$model.save_6taknv$(false);
      (tmp$ = closure$labelInput.v) != null ? (tmp$.focus(), Unit) : null;
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_9($receiver) {
    appendText($receiver, 'Save & New');
    return Unit;
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_10(closure$model) {
    return function (it) {
      closure$model.cancel();
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_11($receiver) {
    appendText($receiver, 'Cancel');
    return Unit;
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_12(closure$model) {
    return function (it) {
      closure$model.delete();
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda$lambda_2(this$) {
    return function (it) {
      set_visible(this$, (it != null ? it.id : null) != null);
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda$lambda_13(closure$model) {
    return function ($receiver) {
      closure$model.option.onNext_qlkmfe$(optionEntryScreen$lambda$lambda$lambda$lambda$lambda_2($receiver));
      appendText($receiver, 'Delete Option');
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda$lambda_4(closure$model, closure$labelInput) {
    return function ($receiver, it) {
      var tmp$;
      addClass($receiver, ['btn-toolbar']);
      btsButton($receiver, ButtonLook.Primary, void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_6(closure$model), void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_7);
      btsButton($receiver, ButtonLook.Default, void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_8(closure$model, closure$labelInput), void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_9);
      btsButton($receiver, void 0, void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_10(closure$model), void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_11);
      nbsp($receiver, 4);
      tmp$ = optionEntryScreen$lambda$lambda$lambda$lambda_12(closure$model);
      btsButton($receiver, ButtonLook.Danger, void 0, void 0, tmp$, void 0, void 0, optionEntryScreen$lambda$lambda$lambda$lambda_13(closure$model));
      return Unit;
    };
  }
  function optionEntryScreen$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      var labelInput = {v: null};
      $receiver.btsFormItemSimple_ekuevp$(void 0, map(closure$model.validation, optionEntryScreen$lambda$lambda$lambda), 'Label', optionEntryScreen$lambda$lambda$lambda_0(closure$model, labelInput));
      $receiver.btsFormItemSimple_ekuevp$(void 0, new Property(State.Companion.Default), 'Quick Summary', optionEntryScreen$lambda$lambda$lambda_1(closure$model));
      $receiver.btsFormItemSimple_ekuevp$(void 0, new Property(State.Companion.Default), 'Details URL', optionEntryScreen$lambda$lambda$lambda_2(closure$model));
      $receiver.btsFormItemSimple_ekuevp$(void 0, new Property(State.Companion.Default), 'Tags', optionEntryScreen$lambda$lambda$lambda_3(closure$model));
      $receiver.btsFormItemSimple_ekuevp$(void 0, new Property(State.Companion.Default), '', optionEntryScreen$lambda$lambda$lambda_4(closure$model, labelInput));
      return Unit;
    };
  }
  function optionEntryScreen$lambda(closure$model) {
    return function ($receiver) {
      inContext('buttonBar', optionEntryScreen$lambda$lambda(closure$model, $receiver));
      btsFormHorizontal($receiver, new Col$Width$Sm(4), new Col$Width$Sm(8), optionEntryScreen$lambda$lambda_0(closure$model));
      return Unit;
    };
  }
  function optionEntryScreen(model) {
    return Div(optionEntryScreen$lambda(model));
  }
  function OptionTagModel(dataSetId, tagCriteriaModel, optionTagIdPair, repository, optionRepository) {
    if (optionTagIdPair === void 0)
      optionTagIdPair = toProperty(null);
    if (repository === void 0)
      repository = Factory_getInstance().optionTagRepository;
    if (optionRepository === void 0)
      optionRepository = Factory_getInstance().optionRepository;
    TagValueModel.call(this, map(optionTagIdPair, OptionTagModel_init$lambda), dataSetId);
    this.tagCriteriaModel = tagCriteriaModel;
    this.optionTagIdPair = optionTagIdPair;
    this.repository = repository;
    this.optionRepository_0 = optionRepository;
    this.optionId = map(this.optionTagIdPair, OptionTagModel$optionId$lambda);
    this.option = flatMapIfNotNull(this.optionId, OptionTagModel$option$lambda(this));
    this.tagId.onNext_qlkmfe$(OptionTagModel_init$lambda_0(this));
  }
  function OptionTagModel$getPercentage$lambda$lambda(it) {
    return it.percentage;
  }
  function OptionTagModel$getPercentage$lambda(this$OptionTagModel, closure$tagId) {
    return function (it) {
      return mapIfNotNull(findFirstOrNullProperty(this$OptionTagModel.repository, new OptionTagByOptionTagIdPair(new OptionTagIdPair(it, closure$tagId))), void 0, OptionTagModel$getPercentage$lambda$lambda);
    };
  }
  OptionTagModel.prototype.getPercentage_apv9qp$ = function (tagId) {
    return flatMapIfNotNull(this.optionId, OptionTagModel$getPercentage$lambda(this, tagId));
  };
  OptionTagModel.prototype.setValue_mz3mdy$ = function (newPercentage) {
    this.setPercentage_q0ln5l$(ensureNotNull(this.tagId.get()), newPercentage);
  };
  function OptionTagModel$setPercentage$lambda(closure$option1, closure$tagId, closure$newPercentage, this$OptionTagModel) {
    return function () {
      var optionId = ensureNotNull(closure$option1.id);
      var optionTagIdPair = new OptionTagIdPair(optionId, closure$tagId);
      if (closure$newPercentage == null) {
        remove(this$OptionTagModel.repository, optionTagIdPair);
      }
       else {
        setPercentage_0(this$OptionTagModel.repository, new OptionTag(optionId, closure$tagId, closure$newPercentage, ensureNotNull(this$OptionTagModel.dataSetId.get())));
      }
      if (!equals(closure$tagId, this$OptionTagModel.tagId.get())) {
        remove(this$OptionTagModel.repository, ensureNotNull(this$OptionTagModel.optionTagIdPair.get()));
      }
      return Unit;
    };
  }
  OptionTagModel.prototype.setPercentage_q0ln5l$ = function (tagId, newPercentage) {
    var option1 = ensureNotNull(this.option.get());
    UndoComponent_getInstance().undoable_u8di54$('Marked ' + option1.label + ' with ' + this.label + '=' + toString(newPercentage) + '%', 'Removed ' + this.label + '=' + toString(newPercentage) + '% from ' + option1.label, OptionTagModel$setPercentage$lambda(option1, tagId, newPercentage, this));
  };
  function OptionTagModel_init$lambda(it) {
    return it != null ? it.tagId : null;
  }
  function OptionTagModel$optionId$lambda(it) {
    return it != null ? it.optionId : null;
  }
  function OptionTagModel$option$lambda(this$OptionTagModel) {
    return function (it) {
      return findProperty(this$OptionTagModel.optionRepository_0, it);
    };
  }
  function OptionTagModel_init$lambda_0(this$OptionTagModel) {
    return function (it) {
      this$OptionTagModel.tagCriteriaModel.criteriaTagId.set_11rb$(it);
      return Unit;
    };
  }
  OptionTagModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionTagModel',
    interfaces: [TagValueModel]
  };
  function buttonForOptionTag$lambda(closure$optionTagIdPair) {
    return function (it) {
      UI_getInstance().showDialog_es32k7$(closure$optionTagIdPair);
      return Unit;
    };
  }
  function buttonForOptionTag($receiver, optionTagIdPair, tag) {
    if (tag === void 0)
      tag = findProperty(Factory_getInstance().tagRepository, optionTagIdPair.tagId);
    var percentage = findPercentageProperty(Factory_getInstance().optionTagRepository, optionTagIdPair);
    buttonForTagValue($receiver, tag, percentage, buttonForOptionTag$lambda(optionTagIdPair));
  }
  function optionTagDialog$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      closure$model.label.onNext_qlkmfe$(optionTagDialog$lambda$lambda$lambda$lambda($receiver));
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda_0(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      closure$model.quickSummary.onNext_qlkmfe$(optionTagDialog$lambda$lambda$lambda$lambda_0($receiver));
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda(closure$model) {
    return function ($receiver) {
      h4($receiver, optionTagDialog$lambda$lambda$lambda(closure$model));
      small($receiver, optionTagDialog$lambda$lambda$lambda_0(closure$model));
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda_1(it) {
    return equals(it, 'Criteria');
  }
  function optionTagDialog$lambda$lambda$lambda_2(it) {
    return equals(it, 'Preference');
  }
  function optionTagDialog$lambda$lambda$lambda_3(it) {
    return equals(it, 'Option');
  }
  function optionTagDialog$lambda$lambda$lambda_4(it) {
    return equals(it, 'Tag');
  }
  function optionTagDialog$lambda$lambda$lambda_5(closure$tagCriteriaModel) {
    return function (it) {
      if (it)
        closure$tagCriteriaModel.preferenceProperty.set_11rb$(false);
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda_6(closure$tagCriteriaModel) {
    return function (it) {
      if (it)
        closure$tagCriteriaModel.preferenceProperty.set_11rb$(true);
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda$lambda$lambda(closure$activeContextName) {
    return function (it) {
      closure$activeContextName.set_11rb$('Criteria');
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda$lambda(closure$activeContextName) {
    return function ($receiver) {
      appendText($receiver, 'Criteria');
      $receiver.onclick = optionTagDialog$lambda$lambda$lambda$lambda$lambda$lambda(closure$activeContextName);
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda_1(closure$activeContextName) {
    return function ($receiver) {
      a($receiver, optionTagDialog$lambda$lambda$lambda$lambda$lambda(closure$activeContextName));
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName) {
    return function (it) {
      closure$activeContextName.set_11rb$('Preference');
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName) {
    return function ($receiver) {
      appendText($receiver, 'Preference');
      $receiver.onclick = optionTagDialog$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName);
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda_2(closure$activeContextName) {
    return function ($receiver) {
      a($receiver, optionTagDialog$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName));
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$activeContextName) {
    return function (it) {
      closure$activeContextName.set_11rb$('Option');
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda$lambda_1(closure$activeContextName) {
    return function ($receiver) {
      appendText($receiver, 'Option');
      $receiver.onclick = optionTagDialog$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$activeContextName);
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda_3(closure$activeContextName) {
    return function ($receiver) {
      a($receiver, optionTagDialog$lambda$lambda$lambda$lambda$lambda_1(closure$activeContextName));
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$activeContextName) {
    return function (it) {
      closure$activeContextName.set_11rb$('Tag');
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda$lambda_2(closure$activeContextName) {
    return function ($receiver) {
      appendText($receiver, 'Edit Tag');
      $receiver.onclick = optionTagDialog$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$activeContextName);
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda_4(closure$activeContextName) {
    return function ($receiver) {
      a($receiver, optionTagDialog$lambda$lambda$lambda$lambda$lambda_2(closure$activeContextName));
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda_7(closure$criteriaTabActive, closure$activeContextName, closure$preferenceTabActive, closure$optionTabActive, closure$tagTabActive) {
    return function ($receiver) {
      $receiver.item_k4rdwa$(closure$criteriaTabActive, void 0, optionTagDialog$lambda$lambda$lambda$lambda_1(closure$activeContextName));
      $receiver.item_k4rdwa$(closure$preferenceTabActive, void 0, optionTagDialog$lambda$lambda$lambda$lambda_2(closure$activeContextName));
      $receiver.item_k4rdwa$(closure$optionTabActive, void 0, optionTagDialog$lambda$lambda$lambda$lambda_3(closure$activeContextName));
      $receiver.item_k4rdwa$(closure$tagTabActive, void 0, optionTagDialog$lambda$lambda$lambda$lambda_4(closure$activeContextName));
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda$lambda_5(it) {
    return it != null ? it.label : null;
  }
  function optionTagDialog$lambda$lambda$lambda_8(closure$tagCriteriaModel, closure$criteriaTabActive, closure$preferenceTabActive, closure$dialog, closure$model, closure$optionTabActive, closure$tagTabActive) {
    return function ($receiver) {
      addClass($receiver, ['tab-content clearfix']);
      tagCriteriaTabsContent($receiver, closure$tagCriteriaModel, closure$criteriaTabActive, closure$preferenceTabActive, closure$dialog);
      optionTagTabContent($receiver, closure$model, map(closure$model.option, optionTagDialog$lambda$lambda$lambda$lambda_5), closure$model.getSelectableTagValues(), closure$optionTabActive, closure$dialog);
      tagTabContent($receiver, closure$tagCriteriaModel, closure$tagTabActive, closure$dialog);
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda_0(closure$model, closure$dialog) {
    return function ($receiver) {
      var activeContextName = new Property('Criteria');
      var criteriaTabActive = map(activeContextName, optionTagDialog$lambda$lambda$lambda_1);
      var preferenceTabActive = map(activeContextName, optionTagDialog$lambda$lambda$lambda_2);
      var optionTabActive = map(activeContextName, optionTagDialog$lambda$lambda$lambda_3);
      var tagTabActive = map(activeContextName, optionTagDialog$lambda$lambda$lambda_4);
      var tagCriteriaModel = closure$model.tagCriteriaModel;
      criteriaTabActive.onNext_qlkmfe$(optionTagDialog$lambda$lambda$lambda_5(tagCriteriaModel));
      preferenceTabActive.onNext_qlkmfe$(optionTagDialog$lambda$lambda$lambda_6(tagCriteriaModel));
      navTabs($receiver, void 0, void 0, optionTagDialog$lambda$lambda$lambda_7(criteriaTabActive, activeContextName, preferenceTabActive, optionTabActive, tagTabActive));
      div($receiver, optionTagDialog$lambda$lambda$lambda_8(tagCriteriaModel, criteriaTabActive, preferenceTabActive, closure$dialog, closure$model, optionTabActive, tagTabActive));
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda_9(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function optionTagDialog$lambda$lambda$lambda_10($receiver) {
    appendText($receiver, 'Close');
    return Unit;
  }
  function optionTagDialog$lambda$lambda_1(closure$dialog) {
    return function ($receiver) {
      btsButton($receiver, void 0, void 0, void 0, optionTagDialog$lambda$lambda$lambda_9(closure$dialog), void 0, void 0, optionTagDialog$lambda$lambda$lambda_10);
      return Unit;
    };
  }
  function optionTagDialog$lambda(closure$model) {
    return function ($receiver, dialog) {
      $receiver.header_htzy0l$(optionTagDialog$lambda$lambda(closure$model));
      $receiver.body_iw61es$(optionTagDialog$lambda$lambda_0(closure$model, dialog));
      $receiver.footer_iw61es$(optionTagDialog$lambda$lambda_1(dialog));
      return Unit;
    };
  }
  function optionTagDialog(model) {
    return prepareDialog(DialogSize.Default, optionTagDialog$lambda(model));
  }
  function optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda(closure$optionLabel) {
    return function ($receiver) {
      closure$optionLabel.onNext_qlkmfe$(optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda$lambda($receiver));
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda$lambda$lambda$lambda(closure$optionLabel) {
    return function ($receiver) {
      appendText($receiver, 'Choose tag for ');
      u($receiver, optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda(closure$optionLabel));
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda$lambda$lambda(closure$optionLabel) {
    return function ($receiver) {
      h4($receiver, optionTagTabContent$lambda$lambda$lambda$lambda$lambda(closure$optionLabel));
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$tagValueModel, closure$dialog) {
    return function ($receiver, tagValue) {
      buttonToSetTagValue($receiver, toProperty(tagValue.tagId), closure$tagValueModel, tagValue.percentage, optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$dialog));
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda$lambda$lambda$lambda_0(closure$selectableTagValues, closure$tagValueModel, closure$dialog) {
    return function ($receiver) {
      repeatLive($receiver, closure$selectableTagValues, void 0, optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$tagValueModel, closure$dialog));
      buttonToSetTagValue($receiver, closure$tagValueModel.tagId, closure$tagValueModel, null, optionTagTabContent$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$dialog));
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda$lambda$lambda_0(closure$selectableTagValues, closure$tagValueModel, closure$dialog) {
    return function ($receiver) {
      div($receiver, optionTagTabContent$lambda$lambda$lambda$lambda$lambda_0(closure$selectableTagValues, closure$tagValueModel, closure$dialog));
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda$lambda(closure$optionLabel, closure$selectableTagValues, closure$tagValueModel, closure$dialog) {
    return function ($receiver) {
      $receiver.btsFormLabel_hrwec9$(optionTagTabContent$lambda$lambda$lambda$lambda(closure$optionLabel));
      $receiver.btsFormInput_iw61es$(optionTagTabContent$lambda$lambda$lambda$lambda_0(closure$selectableTagValues, closure$tagValueModel, closure$dialog));
      return Unit;
    };
  }
  function optionTagTabContent$lambda$lambda(closure$optionLabel, closure$selectableTagValues, closure$tagValueModel, closure$dialog) {
    return function ($receiver) {
      $receiver.btsFormItem_1wd1uy$(void 0, void 0, optionTagTabContent$lambda$lambda$lambda(closure$optionLabel, closure$selectableTagValues, closure$tagValueModel, closure$dialog));
      return Unit;
    };
  }
  function optionTagTabContent$lambda(closure$optionTabActive, closure$optionLabel, closure$selectableTagValues, closure$tagValueModel, closure$dialog) {
    return function ($receiver) {
      setClassPresence($receiver, 'active', closure$optionTabActive);
      addClass($receiver, ['tab-pane']);
      btsFormDefault($receiver, optionTagTabContent$lambda$lambda(closure$optionLabel, closure$selectableTagValues, closure$tagValueModel, closure$dialog));
      return Unit;
    };
  }
  function optionTagTabContent($receiver, tagValueModel, optionLabel, selectableTagValues, optionTabActive, dialog) {
    div($receiver, optionTagTabContent$lambda(optionTabActive, optionLabel, selectableTagValues, tagValueModel, dialog));
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda($receiver) {
    appendText($receiver, 'Which Options to Show');
    return Unit;
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda($receiver) {
    h4($receiver, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda);
    return Unit;
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda$lambda(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver, tagValue) {
      buttonToSetTagCriteriaValue($receiver, toProperty(tagValue.tagId), closure$tagCriteriaModel, tagValue.percentage >= 50, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda$lambda(closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda_0(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      repeatLive($receiver, closure$tagCriteriaModel.getSelectableTagCriteriaValues(), void 0, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda(closure$tagCriteriaModel, closure$dialog));
      buttonToSetTagCriteriaValue($receiver, closure$tagCriteriaModel.tagId, closure$tagCriteriaModel, null, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda_0(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      div($receiver, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda_0(closure$tagCriteriaModel, closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      $receiver.btsFormLabel_hrwec9$(tagCriteriaTabsContent$lambda$lambda$lambda$lambda);
      $receiver.btsFormInput_iw61es$(tagCriteriaTabsContent$lambda$lambda$lambda$lambda_0(closure$tagCriteriaModel, closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      $receiver.btsFormItem_1wd1uy$(void 0, void 0, tagCriteriaTabsContent$lambda$lambda$lambda(closure$tagCriteriaModel, closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda(closure$criteriaTabActive, closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      setClassPresence($receiver, 'active', closure$criteriaTabActive);
      addClass($receiver, ['tab-pane']);
      btsFormDefault($receiver, tagCriteriaTabsContent$lambda$lambda(closure$tagCriteriaModel, closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda_1($receiver) {
    appendText($receiver, 'Which Options to Prefer');
    return Unit;
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda_1($receiver) {
    h4($receiver, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda_1);
    return Unit;
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver, tagValue) {
      buttonToSetTagCriteriaValue($receiver, toProperty(tagValue.tagId), closure$tagCriteriaModel, tagValue.percentage >= 50, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda_2(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      repeatLive($receiver, closure$tagCriteriaModel.getSelectableTagCriteriaValues(), void 0, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$tagCriteriaModel, closure$dialog));
      buttonToSetTagCriteriaValue($receiver, closure$tagCriteriaModel.tagId, closure$tagCriteriaModel, null, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda$lambda_2(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      div($receiver, tagCriteriaTabsContent$lambda$lambda$lambda$lambda$lambda_2(closure$tagCriteriaModel, closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda$lambda_0(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      $receiver.btsFormLabel_hrwec9$(tagCriteriaTabsContent$lambda$lambda$lambda$lambda_1);
      $receiver.btsFormInput_iw61es$(tagCriteriaTabsContent$lambda$lambda$lambda$lambda_2(closure$tagCriteriaModel, closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda$lambda_0(closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      $receiver.btsFormItem_1wd1uy$(void 0, void 0, tagCriteriaTabsContent$lambda$lambda$lambda_0(closure$tagCriteriaModel, closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent$lambda_0(closure$preferenceTabActive, closure$tagCriteriaModel, closure$dialog) {
    return function ($receiver) {
      setClassPresence($receiver, 'active', closure$preferenceTabActive);
      addClass($receiver, ['tab-pane']);
      btsFormDefault($receiver, tagCriteriaTabsContent$lambda$lambda_0(closure$tagCriteriaModel, closure$dialog));
      return Unit;
    };
  }
  function tagCriteriaTabsContent($receiver, tagCriteriaModel, criteriaTabActive, preferenceTabActive, dialog) {
    div($receiver, tagCriteriaTabsContent$lambda(criteriaTabActive, tagCriteriaModel, dialog));
    div($receiver, tagCriteriaTabsContent$lambda_0(preferenceTabActive, tagCriteriaModel, dialog));
  }
  function tagTabContent$lambda$lambda(closure$tagModel, closure$dialog) {
    return function (it) {
      closure$tagModel.deleteTag();
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagTabContent$lambda$lambda_0($receiver) {
    addClass($receiver, ['pull-left']);
    appendText($receiver, 'Delete Tag');
    return Unit;
  }
  function tagTabContent$lambda(closure$tagTabActive, closure$tagModel, closure$dialog) {
    return function ($receiver) {
      setClassPresence($receiver, 'active', closure$tagTabActive);
      addClass($receiver, ['tab-pane']);
      tagEditForm($receiver, closure$tagModel);
      btsButton($receiver, ButtonLook.Danger, void 0, void 0, tagTabContent$lambda$lambda(closure$tagModel, closure$dialog), void 0, void 0, tagTabContent$lambda$lambda_0);
      return Unit;
    };
  }
  function tagTabContent($receiver, tagModel, tagTabActive, dialog) {
    div($receiver, tagTabContent$lambda(tagTabActive, tagModel, dialog));
  }
  function OptionsModel(dataSetId, repository, tagRepository, optionTagRepository, tagTagRepository, dataSetRepository) {
    OptionsModel$Companion_getInstance();
    if (repository === void 0)
      repository = Factory_getInstance().optionRepository;
    if (tagRepository === void 0)
      tagRepository = Factory_getInstance().tagRepository;
    if (optionTagRepository === void 0)
      optionTagRepository = Factory_getInstance().optionTagRepository;
    if (tagTagRepository === void 0)
      tagTagRepository = Factory_getInstance().tagTagRepository;
    if (dataSetRepository === void 0)
      dataSetRepository = Factory_getInstance().dataSetRepository;
    this.dataSetId = dataSetId;
    this.repository = repository;
    this.tagRepository = tagRepository;
    this.optionTagRepository = optionTagRepository;
    this.tagTagRepository = tagTagRepository;
    this.dataSetRepository = dataSetRepository;
    this.dataSet = flatMapIfNotNull(this.dataSetId, OptionsModel$dataSet$lambda(this));
    this.showTextFilter = new Property(false);
    this.textFilter = new Property('');
    this.textFilterLowerCaseParts = map(this.textFilter, OptionsModel$textFilterLowerCaseParts$lambda);
    this.isCriteriaExpanded = toProperty(false);
    this.tagCriteriaList = new Property(emptyList());
    this.effectiveTagCriteriaList = mapWith(this.tagCriteriaList, listProperty(this.tagTagRepository), OptionsModel$effectiveTagCriteriaList$lambda(this));
    this.alphabeticalTagIds = alphabeticalTagIds(this.tagRepository, this.dataSetId);
    this.filteredTagIds_0 = map(zip(this.alphabeticalTagIds, this.textFilterLowerCaseParts), OptionsModel$filteredTagIds$lambda(this));
    this.tagIdGroups = map(zip_1(this.tagCriteriaList, this.isCriteriaExpanded, this.alphabeticalTagIds, this.filteredTagIds_0), OptionsModel$tagIdGroups$lambda);
    this.options = flatMapIfNotNull(this.dataSetId, OptionsModel$options$lambda(this));
    this.currentSort = new Property(null);
    this.optionRows = mapAsDefault(async(zip_0(this.options, this.effectiveTagCriteriaList, listProperty(this.optionTagRepository))), OptionsModel$optionRows$lambda(this));
    this.backHash = map(this.dataSetId, OptionsModel$backHash$lambda);
    this.tagRepository.addListener_56i51t$(new OptionsModel_init$ObjectLiteral(this));
    this.dataSetId.onNext_qlkmfe$(OptionsModel_init$lambda(this));
  }
  OptionsModel.prototype.resort = function () {
    this.optionRows.set_11rb$(this.optionRows.get());
  };
  OptionsModel.prototype.delete_s47mgy$ = function (optionId) {
    this.repository.remove_83a66n$(optionId);
  };
  OptionsModel.prototype.saveNewTag_g3da2i$ = function (optionId, newTagLabel, htmlInputElement, event, newPercentage) {
    if (newPercentage === void 0)
      newPercentage = 100;
    var newLabel = newTagLabel.get();
    var dataSetIdValue = this.dataSetId.get();
    if (!isBlank(newLabel) && dataSetIdValue != null) {
      var newTagId = findOrCreate(this.tagRepository, newLabel, dataSetIdValue);
      if (optionId != null) {
        setPercentage_0(this.optionTagRepository, new OptionTag(optionId, newTagId, newPercentage, dataSetIdValue));
      }
       else {
        this.setTagCriteriaValue_ifydeo$(newTagId, newPercentage >= 50, false);
      }
      event.preventDefault();
      htmlInputElement.focus();
    }
    newTagLabel.set_11rb$('');
  };
  function OptionsModel$setTagCriteriaValue$lambda$lambda(closure$tagId) {
    return function (it) {
      var tmp$;
      return (tmp$ = it.tagId) != null ? tmp$.equals(closure$tagId) : null;
    };
  }
  function OptionsModel$setTagCriteriaValue$lambda(closure$newValue, closure$tagId, closure$preference) {
    return function (list) {
      if (closure$newValue != null) {
        var indexOfFirst$result;
        indexOfFirst$break: do {
          var tmp$;
          var index = 0;
          tmp$ = list.iterator();
          while (tmp$.hasNext()) {
            var item = tmp$.next();
            var closure$tagId_0 = closure$tagId;
            var tmp$_0;
            if ((tmp$_0 = item.tagId) != null ? tmp$_0.equals(closure$tagId_0) : null) {
              indexOfFirst$result = index;
              break indexOfFirst$break;
            }
            index = index + 1 | 0;
          }
          indexOfFirst$result = -1;
        }
         while (false);
        var index_0 = indexOfFirst$result;
        if (index_0 >= 0) {
          list.set_wxm5ur$(index_0, list.get_za3lpa$(index_0).copy_shiqq7$(void 0, closure$newValue, closure$preference));
        }
         else {
          list.add_11rb$(new TagCriteria(closure$tagId, closure$newValue, closure$preference));
        }
      }
       else {
        removeAll(list, OptionsModel$setTagCriteriaValue$lambda$lambda(closure$tagId));
      }
      return Unit;
    };
  }
  OptionsModel.prototype.setTagCriteriaValue_ifydeo$ = function (tagId, newValue, preference) {
    if (preference === void 0)
      preference = false;
    modifyList(this.tagCriteriaList, OptionsModel$setTagCriteriaValue$lambda(newValue, tagId, preference));
  };
  function OptionsModel$getTagCriteriaProperty$lambda(closure$tagId) {
    return function (list) {
      var firstOrNull$result;
      firstOrNull$break: do {
        var tmp$;
        tmp$ = list.iterator();
        while (tmp$.hasNext()) {
          var element = tmp$.next();
          var closure$tagId_0 = closure$tagId;
          var tmp$_0;
          if ((tmp$_0 = element.tagId) != null ? tmp$_0.equals(closure$tagId_0) : null) {
            firstOrNull$result = element;
            break firstOrNull$break;
          }
        }
        firstOrNull$result = null;
      }
       while (false);
      return firstOrNull$result;
    };
  }
  OptionsModel.prototype.getTagCriteriaProperty_apv9qp$ = function (tagId) {
    return mapIfNotNull(this.tagCriteriaList, void 0, OptionsModel$getTagCriteriaProperty$lambda(tagId));
  };
  function OptionsModel$getTagCriteriaValueProperty$lambda(it) {
    return it != null ? it.value : null;
  }
  OptionsModel.prototype.getTagCriteriaValueProperty_apv9qp$ = function (tagId) {
    return map(this.getTagCriteriaProperty_apv9qp$(tagId), OptionsModel$getTagCriteriaValueProperty$lambda);
  };
  OptionsModel.prototype.toggleCriteriaExpanded = function () {
    this.isCriteriaExpanded.set_11rb$(!this.isCriteriaExpanded.get());
  };
  function OptionsModel$Companion() {
    OptionsModel$Companion_instance = this;
  }
  OptionsModel$Companion.prototype.toUrl_5mhfxq$ = function (dataSetId) {
    return dataSetId == null ? DataSetModel$Companion_getInstance().toUrl_5mhfxq$(dataSetId) : '#options' + '/' + toString(dataSetId);
  };
  OptionsModel$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var OptionsModel$Companion_instance = null;
  function OptionsModel$Companion_getInstance() {
    if (OptionsModel$Companion_instance === null) {
      new OptionsModel$Companion();
    }
    return OptionsModel$Companion_instance;
  }
  function OptionsModel$dataSet$lambda(this$OptionsModel) {
    return function (it) {
      return findProperty(this$OptionsModel.dataSetRepository, it);
    };
  }
  function OptionsModel$textFilterLowerCaseParts$lambda(it) {
    var $receiver = split(it.toLowerCase(), Kotlin.charArrayOf(32, 44));
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (!equals(element, ''))
        destination.add_11rb$(element);
    }
    return destination;
  }
  function OptionsModel$effectiveTagCriteriaList$lambda(this$OptionsModel) {
    return function (criteriaList, f) {
      var destination = ArrayList_init(collectionSizeOrDefault(criteriaList, 10));
      var tmp$;
      tmp$ = criteriaList.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(getEffectiveTagCriteria(this$OptionsModel.tagTagRepository, item));
      }
      return destination;
    };
  }
  function OptionsModel$filteredTagIds$lambda(this$OptionsModel) {
    return function (f) {
      var allTagIds = f.component1()
      , filterParts = f.component2();
      var tmp$;
      if (filterParts.isEmpty())
        tmp$ = null;
      else {
        var destination = ArrayList_init();
        var tmp$_0;
        tmp$_0 = allTagIds.iterator();
        while (tmp$_0.hasNext()) {
          var element = tmp$_0.next();
          var tmp$_1, tmp$_2;
          if ((tmp$_2 = (tmp$_1 = this$OptionsModel.tagRepository.find_83a66n$(element)) != null ? tmp$_1.containsAllLowerCaseSubstrings_mhpeer$(filterParts) : null) != null ? tmp$_2 : false)
            destination.add_11rb$(element);
        }
        tmp$ = destination;
      }
      return tmp$;
    };
  }
  function OptionsModel$tagIdGroups$lambda(f) {
    var criteriaList = f.component1()
    , expanded = f.component2()
    , tagIds = f.component3()
    , filteredTagIds = f.component4();
    var tmp$;
    var destination = ArrayList_init();
    var tmp$_0;
    tmp$_0 = criteriaList.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      if (!element.preference)
        destination.add_11rb$(element);
    }
    var destination_0 = ArrayList_init(collectionSizeOrDefault(destination, 10));
    var tmp$_1;
    tmp$_1 = destination.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      destination_0.add_11rb$(item.tagId);
    }
    var requiredTagIds = destination_0;
    var destination_1 = ArrayList_init();
    var tmp$_2;
    tmp$_2 = criteriaList.iterator();
    while (tmp$_2.hasNext()) {
      var element_0 = tmp$_2.next();
      if (element_0.preference)
        destination_1.add_11rb$(element_0);
    }
    var destination_2 = ArrayList_init(collectionSizeOrDefault(destination_1, 10));
    var tmp$_3;
    tmp$_3 = destination_1.iterator();
    while (tmp$_3.hasNext()) {
      var item_0 = tmp$_3.next();
      destination_2.add_11rb$(item_0.tagId);
    }
    var preferredTagIds = destination_2;
    if (filteredTagIds != null) {
      tmp$ = minus(minus(filteredTagIds, requiredTagIds), preferredTagIds);
    }
     else {
      tmp$ = expanded ? minus(minus(tagIds, requiredTagIds), preferredTagIds) : emptyList();
    }
    var availableTagIds = tmp$;
    return new TagIdGroups(requiredTagIds, preferredTagIds, availableTagIds);
  }
  function OptionsModel$options$lambda(this$OptionsModel) {
    return function (it) {
      return listProperty(this$OptionsModel.repository, new OptionByDataSetId(it));
    };
  }
  function OptionsModel$optionRows$lambda(this$OptionsModel) {
    return function (f) {
      var options = f.component1()
      , criteriaList = f.component2();
      var tmp$;
      if (options != null) {
        var destination = ArrayList_init(collectionSizeOrDefault(options, 10));
        var tmp$_0;
        tmp$_0 = options.iterator();
        while (tmp$_0.hasNext()) {
          var item = tmp$_0.next();
          var tmp$_1 = destination.add_11rb$;
          var this$OptionsModel_0 = this$OptionsModel;
          var optionId = ensureNotNull(item.id);
          var tagValues = list(this$OptionsModel_0.optionTagRepository, OptionTagValueField_getInstance(), new OptionTagByOptionId(optionId));
          tmp$_1.call(destination, new OptionRow(optionId, item, this$OptionsModel_0.alphabeticalTagIds, tagValues, criteriaList));
        }
        tmp$ = destination;
      }
       else
        tmp$ = null;
      return tmp$;
    };
  }
  function OptionsModel$backHash$lambda(it) {
    return DataSetModel$Companion_getInstance().toUrl_5mhfxq$(it);
  }
  function OptionsModel_init$ObjectLiteral(this$OptionsModel) {
    this.this$OptionsModel = this$OptionsModel;
  }
  OptionsModel_init$ObjectLiteral.prototype.onSaved_xwzc9q$ = function (original, replacementWithID) {
  };
  function OptionsModel_init$ObjectLiteral$onRemoved$lambda$lambda(closure$item) {
    return function (it) {
      return equals(it.tagId, closure$item.id);
    };
  }
  function OptionsModel_init$ObjectLiteral$onRemoved$lambda(closure$item) {
    return function (it) {
      removeAll(it, OptionsModel_init$ObjectLiteral$onRemoved$lambda$lambda(closure$item));
      return Unit;
    };
  }
  OptionsModel_init$ObjectLiteral.prototype.onRemoved_11rb$ = function (item) {
    modifyList(this.this$OptionsModel.tagCriteriaList, OptionsModel_init$ObjectLiteral$onRemoved$lambda(item));
  };
  OptionsModel_init$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [RepositoryListener]
  };
  function OptionsModel_init$lambda(this$OptionsModel) {
    return function (it) {
      this$OptionsModel.tagCriteriaList.set_11rb$(emptyList());
      return Unit;
    };
  }
  OptionsModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionsModel',
    interfaces: []
  };
  function TagIdGroups(requiredTagIds, preferredTagIds, availableTagIds) {
    this.requiredTagIds = requiredTagIds;
    this.preferredTagIds = preferredTagIds;
    this.availableTagIds = availableTagIds;
  }
  TagIdGroups.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagIdGroups',
    interfaces: []
  };
  TagIdGroups.prototype.component1 = function () {
    return this.requiredTagIds;
  };
  TagIdGroups.prototype.component2 = function () {
    return this.preferredTagIds;
  };
  TagIdGroups.prototype.component3 = function () {
    return this.availableTagIds;
  };
  TagIdGroups.prototype.copy_hq0gt8$ = function (requiredTagIds, preferredTagIds, availableTagIds) {
    return new TagIdGroups(requiredTagIds === void 0 ? this.requiredTagIds : requiredTagIds, preferredTagIds === void 0 ? this.preferredTagIds : preferredTagIds, availableTagIds === void 0 ? this.availableTagIds : availableTagIds);
  };
  TagIdGroups.prototype.toString = function () {
    return 'TagIdGroups(requiredTagIds=' + Kotlin.toString(this.requiredTagIds) + (', preferredTagIds=' + Kotlin.toString(this.preferredTagIds)) + (', availableTagIds=' + Kotlin.toString(this.availableTagIds)) + ')';
  };
  TagIdGroups.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.requiredTagIds) | 0;
    result = result * 31 + Kotlin.hashCode(this.preferredTagIds) | 0;
    result = result * 31 + Kotlin.hashCode(this.availableTagIds) | 0;
    return result;
  };
  TagIdGroups.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.requiredTagIds, other.requiredTagIds) && Kotlin.equals(this.preferredTagIds, other.preferredTagIds) && Kotlin.equals(this.availableTagIds, other.availableTagIds)))));
  };
  function OptionRow(optionId, option, alphabeticalTagIds, tagValues, effectiveTagCriteriaList) {
    this.optionId = optionId;
    this.option = option;
    this.alphabeticalTagIds = alphabeticalTagIds;
    this.tagValues = tagValues;
    this.effectiveTagCriteriaList = effectiveTagCriteriaList;
    this.isExpanded = toProperty(false);
    var $receiver = this.effectiveTagCriteriaList;
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      var tmp$_0, tmp$_1;
      destination.add_11rb$((tmp$_1 = (tmp$_0 = item.findDecidingKeyTagValue_onfp55$(this.tagValues)) != null ? tmp$_0.tagId : null) != null ? tmp$_1 : item.pivotalTagId);
    }
    this.decidingTagIds_0 = destination;
    var tmp$_2 = this.decidingTagIds_0;
    var $receiver_0 = this.tagValues;
    var destination_0 = ArrayList_init(collectionSizeOrDefault($receiver_0, 10));
    var tmp$_3;
    tmp$_3 = $receiver_0.iterator();
    while (tmp$_3.hasNext()) {
      var item_0 = tmp$_3.next();
      destination_0.add_11rb$(item_0.tagId);
    }
    this.unexpandedTagIds_0 = distinct(plus(tmp$_2, destination_0));
    this.optionTagIdPairsToShow = map(flatMap(this.isExpanded, OptionRow$optionTagIdPairsToShow$lambda(this)), OptionRow$optionTagIdPairsToShow$lambda_0(this));
    this.matchScore = matchScore(this.effectiveTagCriteriaList, this.tagValues);
  }
  OptionRow.prototype.toggleExpanded = function () {
    this.isExpanded.set_11rb$(!this.isExpanded.get());
  };
  function OptionRow$optionTagIdPairsToShow$lambda(this$OptionRow) {
    return function (expanded) {
      return expanded ? this$OptionRow.alphabeticalTagIds : toProperty(this$OptionRow.unexpandedTagIds_0);
    };
  }
  function OptionRow$optionTagIdPairsToShow$lambda_0(this$OptionRow) {
    return function (it) {
      var destination = ArrayList_init(collectionSizeOrDefault(it, 10));
      var tmp$;
      tmp$ = it.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(new OptionTagIdPair(this$OptionRow.optionId, item));
      }
      return destination;
    };
  }
  OptionRow.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionRow',
    interfaces: []
  };
  OptionRow.prototype.component1 = function () {
    return this.optionId;
  };
  OptionRow.prototype.component2 = function () {
    return this.option;
  };
  OptionRow.prototype.component3 = function () {
    return this.alphabeticalTagIds;
  };
  OptionRow.prototype.component4 = function () {
    return this.tagValues;
  };
  OptionRow.prototype.component5 = function () {
    return this.effectiveTagCriteriaList;
  };
  OptionRow.prototype.copy_jlia54$ = function (optionId, option, alphabeticalTagIds, tagValues, effectiveTagCriteriaList) {
    return new OptionRow(optionId === void 0 ? this.optionId : optionId, option === void 0 ? this.option : option, alphabeticalTagIds === void 0 ? this.alphabeticalTagIds : alphabeticalTagIds, tagValues === void 0 ? this.tagValues : tagValues, effectiveTagCriteriaList === void 0 ? this.effectiveTagCriteriaList : effectiveTagCriteriaList);
  };
  OptionRow.prototype.toString = function () {
    return 'OptionRow(optionId=' + Kotlin.toString(this.optionId) + (', option=' + Kotlin.toString(this.option)) + (', alphabeticalTagIds=' + Kotlin.toString(this.alphabeticalTagIds)) + (', tagValues=' + Kotlin.toString(this.tagValues)) + (', effectiveTagCriteriaList=' + Kotlin.toString(this.effectiveTagCriteriaList)) + ')';
  };
  OptionRow.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.optionId) | 0;
    result = result * 31 + Kotlin.hashCode(this.option) | 0;
    result = result * 31 + Kotlin.hashCode(this.alphabeticalTagIds) | 0;
    result = result * 31 + Kotlin.hashCode(this.tagValues) | 0;
    result = result * 31 + Kotlin.hashCode(this.effectiveTagCriteriaList) | 0;
    return result;
  };
  OptionRow.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.optionId, other.optionId) && Kotlin.equals(this.option, other.option) && Kotlin.equals(this.alphabeticalTagIds, other.alphabeticalTagIds) && Kotlin.equals(this.tagValues, other.tagValues) && Kotlin.equals(this.effectiveTagCriteriaList, other.effectiveTagCriteriaList)))));
  };
  function optionsScreen$lambda$lambda$lambda(it) {
    return toString(it != null ? it.label : null) + ' Options';
  }
  function optionsScreen$lambda$lambda$lambda_0(it) {
    return DataSetModel$Companion_getInstance().toUrl_5mhfxq$(it);
  }
  function optionsScreen$lambda$lambda(closure$model, this$) {
    return function () {
      buttonBar(this$, closure$model.backHash, map(closure$model.dataSet, optionsScreen$lambda$lambda$lambda), mapIfNotNull(closure$model.dataSetId, void 0, optionsScreen$lambda$lambda$lambda_0));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda_0($receiver) {
    $receiver.textContent = 'Which Options to Show';
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda_1(closure$model, closure$searchInput) {
    return function (f) {
      var tmp$;
      closure$model.showTextFilter.set_11rb$(true);
      (tmp$ = closure$searchInput.v) != null ? (tmp$.focus(), Unit) : null;
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      set_visible(this$, !it);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_2(closure$model) {
    return function ($receiver) {
      closure$model.showTextFilter.onNext_qlkmfe$(optionsScreen$lambda$lambda$lambda$lambda($receiver));
      flaticon($receiver, 'magnifier-tool-2');
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda_0(this$) {
    return function (it) {
      set_visible(this$, it);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda_1($receiver) {
    addClass($receiver, ['form-control']);
    $receiver.placeholder = 'Find what?';
    $receiver.size = 15;
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda$lambda_2(closure$model) {
    return function (f) {
      closure$model.showTextFilter.set_11rb$(false);
      closure$model.textFilter.set_11rb$('');
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda_3($receiver) {
    appendText($receiver, String.fromCharCode(unboxChar(text.Typography.times)));
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda_3(closure$model, closure$searchInput) {
    return function ($receiver) {
      var tmp$;
      closure$model.showTextFilter.onNext_qlkmfe$(optionsScreen$lambda$lambda$lambda$lambda_0($receiver));
      closure$searchInput.v = textInput($receiver, closure$model.textFilter, void 0, void 0, void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda_1);
      tmp$ = ButtonSize.Default;
      btsButton($receiver, ButtonLook.Default, tmp$, void 0, optionsScreen$lambda$lambda$lambda$lambda_2(closure$model), void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda_3);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_4($receiver) {
    nbsp($receiver, 2);
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda_5(it) {
    return it.requiredTagIds;
  }
  function optionsScreen$lambda$lambda$lambda_6(closure$model) {
    return function ($receiver, tagId) {
      buttonForTagCriteria($receiver, tagId, closure$model);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_7(it) {
    return it.availableTagIds;
  }
  function optionsScreen$lambda$lambda$lambda_8(closure$model) {
    return function ($receiver, tagId) {
      buttonForTagCriteria($receiver, tagId, closure$model);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_9(closure$model, closure$input) {
    return function (it) {
      closure$model.toggleCriteriaExpanded();
      ensureNotNull(closure$input.v).focus();
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda_4(this$) {
    return function (it) {
      this$.textContent = it ? '<<' : '...';
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_10(closure$model) {
    return function ($receiver) {
      closure$model.isCriteriaExpanded.onNext_qlkmfe$(optionsScreen$lambda$lambda$lambda$lambda_4($receiver));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda_5(closure$model, closure$newTagLabel, this$) {
    return function (event) {
      closure$model.saveNewTag_g3da2i$(null, closure$newTagLabel, this$, event);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_11(closure$model, closure$newTagLabel) {
    return function ($receiver) {
      addClass($receiver, ['form-control']);
      $receiver.placeholder = 'Desired Tag';
      $receiver.size = 15;
      $receiver.onblur = optionsScreen$lambda$lambda$lambda$lambda_5(closure$model, closure$newTagLabel, $receiver);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_12(it) {
    return it.preferredTagIds;
  }
  function optionsScreen$lambda$lambda$lambda$lambda_6(this$) {
    return function (it) {
      this$.textContent = nonEmpty(it) ? ' preferring: ' : '';
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_13(closure$preferredTagIds) {
    return function ($receiver) {
      closure$preferredTagIds.onNext_qlkmfe$(optionsScreen$lambda$lambda$lambda$lambda_6($receiver));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_14(closure$model) {
    return function ($receiver, tagId) {
      buttonForTagCriteria($receiver, tagId, closure$model);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda_1(closure$model) {
    return function ($receiver) {
      var tmp$;
      addClass($receiver, ['container-fluid']);
      var searchInput = {v: null};
      tmp$ = ButtonSize.Default;
      btsButton($receiver, ButtonLook.Default, tmp$, void 0, optionsScreen$lambda$lambda$lambda_1(closure$model, searchInput), void 0, void 0, optionsScreen$lambda$lambda$lambda_2(closure$model));
      span($receiver, optionsScreen$lambda$lambda$lambda_3(closure$model, searchInput));
      span($receiver, optionsScreen$lambda$lambda$lambda_4);
      repeatLive($receiver, map(closure$model.tagIdGroups, optionsScreen$lambda$lambda$lambda_5), void 0, optionsScreen$lambda$lambda$lambda_6(closure$model));
      var newTagLabel = new Property('');
      repeatLive($receiver, map(closure$model.tagIdGroups, optionsScreen$lambda$lambda$lambda_7), void 0, optionsScreen$lambda$lambda$lambda_8(closure$model));
      var input = {v: null};
      btsButton($receiver, void 0, void 0, void 0, optionsScreen$lambda$lambda$lambda_9(closure$model, input), void 0, void 0, optionsScreen$lambda$lambda$lambda_10(closure$model));
      input.v = textInput($receiver, newTagLabel, void 0, void 0, void 0, void 0, optionsScreen$lambda$lambda$lambda_11(closure$model, newTagLabel));
      var preferredTagIds = map(closure$model.tagIdGroups, optionsScreen$lambda$lambda$lambda_12);
      span($receiver, optionsScreen$lambda$lambda$lambda_13(preferredTagIds));
      repeatLive($receiver, preferredTagIds, void 0, optionsScreen$lambda$lambda$lambda_14(closure$model));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda(it) {
    return it.option.label;
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0($receiver) {
    appendText($receiver, 'Options');
    return Unit;
  }
  var wrapFunction = Kotlin.wrapFunction;
  var compareBy$lambda = wrapFunction(function () {
    var compareValues = Kotlin.kotlin.comparisons.compareValues_s00gnj$;
    return function (closure$selector) {
      return function (a, b) {
        var selector = closure$selector;
        return compareValues(selector(a), selector(b));
      };
    };
  });
  var Comparator = Kotlin.kotlin.Comparator;
  function Comparator$ObjectLiteral(closure$comparison) {
    this.closure$comparison = closure$comparison;
  }
  Comparator$ObjectLiteral.prototype.compare = function (a, b) {
    return this.closure$comparison(a, b);
  };
  Comparator$ObjectLiteral.$metadata$ = {kind: Kind_CLASS, interfaces: [Comparator]};
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      $receiver.className = 'text-left';
      sortControlWithArrow($receiver, closure$model.currentSort, new Comparator$ObjectLiteral(compareBy$lambda(optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda)), void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1(it) {
    return -it.matchScore.probability;
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_2(it) {
    return it.option.label;
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_3($receiver) {
    appendText($receiver, 'Tags');
    return Unit;
  }
  var compareBy$lambda_0 = wrapFunction(function () {
    var compareValues = Kotlin.kotlin.comparisons.compareValues_s00gnj$;
    return function (closure$selector) {
      return function (a, b) {
        var selector = closure$selector;
        return compareValues(selector(a), selector(b));
      };
    };
  });
  function Comparator$ObjectLiteral_0(closure$comparison) {
    this.closure$comparison = closure$comparison;
  }
  Comparator$ObjectLiteral_0.prototype.compare = function (a, b) {
    return this.closure$comparison(a, b);
  };
  Comparator$ObjectLiteral_0.$metadata$ = {kind: Kind_CLASS, interfaces: [Comparator]};
  var compareBy$lambda_1 = wrapFunction(function () {
    var compareValues = Kotlin.kotlin.comparisons.compareValues_s00gnj$;
    return function (closure$selector) {
      return function (a, b) {
        var selector = closure$selector;
        return compareValues(selector(a), selector(b));
      };
    };
  });
  function Comparator$ObjectLiteral_1(closure$comparison) {
    this.closure$comparison = closure$comparison;
  }
  Comparator$ObjectLiteral_1.prototype.compare = function (a, b) {
    return this.closure$comparison(a, b);
  };
  Comparator$ObjectLiteral_1.$metadata$ = {kind: Kind_CLASS, interfaces: [Comparator]};
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      $receiver.className = 'text-left';
      sortControlWithArrow($receiver, closure$model.currentSort, then(new Comparator$ObjectLiteral_0(compareBy$lambda_0(optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1)), new Comparator$ObjectLiteral_1(compareBy$lambda_1(optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_2))), void 0, true, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_3);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_1($receiver) {
    $receiver.className = 'text-right';
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      th($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda(closure$model));
      th($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$model));
      th($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_1);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda_7(closure$model) {
    return function ($receiver) {
      addClass($receiver, ['hidden-tn hidden-xxs']);
      tr($receiver, void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda$lambda(closure$model));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$optionRow) {
    return function (textFilterLowerCaseParts) {
      return !closure$optionRow.option.containsAllLowerCaseSubstrings_mhpeer$(textFilterLowerCaseParts);
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda($receiver, it) {
    $receiver.textContent = it.truncatedLabel;
    $receiver.title = !equals(it.truncatedLabel, it.label) ? it.label : '';
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_4(closure$optionRow) {
    return function ($receiver) {
      $receiver.className = 'text-left name';
      editOnClick($receiver, closure$optionRow.option, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0($receiver, it) {
    var tmp$;
    $receiver.textContent = it.truncatedQuickSummary;
    $receiver.title = !equals(it.truncatedQuickSummary, it.quickSummary) ? (tmp$ = it.quickSummary) != null ? tmp$ : '' : '';
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_5(closure$optionRow) {
    return function ($receiver) {
      $receiver.className = 'text-left';
      editOnClick($receiver, closure$optionRow.option, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_3(closure$optionRow) {
    return function ($receiver) {
      h4($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_4(closure$optionRow));
      div($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_5(closure$optionRow));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1($receiver, optionTagIdPair) {
    buttonForOptionTag($receiver, optionTagIdPair);
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$optionRow, closure$input) {
    return function (it) {
      closure$optionRow.toggleExpanded();
      ensureNotNull(closure$input.v).focus();
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      this$.textContent = it ? '<<' : '...';
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_3(closure$optionRow) {
    return function ($receiver) {
      closure$optionRow.isExpanded.onNext_qlkmfe$(optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda($receiver));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$model, closure$optionId, closure$newTagLabel, this$) {
    return function (event) {
      closure$model.saveNewTag_g3da2i$(closure$optionId, closure$newTagLabel, this$, event);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_4(closure$model, closure$optionId, closure$newTagLabel) {
    return function ($receiver) {
      addClass($receiver, ['form-control']);
      $receiver.placeholder = 'New Tag';
      $receiver.size = 15;
      $receiver.onblur = optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$model, closure$optionId, closure$newTagLabel, $receiver);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_6(closure$optionRow, closure$model, closure$optionId) {
    return function ($receiver) {
      $receiver.className = 'text-left';
      repeatLive($receiver, closure$optionRow.optionTagIdPairsToShow, void 0, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1);
      var input = {v: null};
      btsButton($receiver, void 0, void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$optionRow, input), void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_3(closure$optionRow));
      var newTagLabel = new Property('');
      input.v = textInput($receiver, newTagLabel, void 0, void 0, void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_4(closure$model, closure$optionId, newTagLabel));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_4(closure$optionRow, closure$model, closure$optionId) {
    return function ($receiver) {
      div($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_6(closure$optionRow, closure$model, closure$optionId));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_5(closure$optionRow) {
    return function ($receiver) {
      $receiver.className = 'small';
      $receiver.textContent = 'Score: ' + closure$optionRow.matchScore.probability * 100 + '%';
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_7(closure$optionRow) {
    return function ($receiver) {
      $receiver.className = 'text-right';
      div($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_5(closure$optionRow));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_5(closure$optionRow) {
    return function ($receiver) {
      div($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda$lambda_7(closure$optionRow));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda_0(closure$optionId, closure$optionRow, closure$model) {
    return function ($receiver) {
      $receiver.id = 'option-row-' + closure$optionId;
      if (closure$optionRow.matchScore.isMatchWithAllTags)
        addClass($receiver, ['match-true']);
      else
        removeClass($receiver, ['match-true']);
      if (closure$optionRow.matchScore.isMatchWithMissingTags)
        addClass($receiver, ['match-null']);
      else
        removeClass($receiver, ['match-null']);
      if (closure$optionRow.matchScore.isNonMatch)
        addClass($receiver, ['match-false']);
      else
        removeClass($receiver, ['match-false']);
      setClassPresence($receiver, 'filter-text-match-false', map(closure$model.textFilterLowerCaseParts, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$optionRow)));
      td($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_3(closure$optionRow));
      td($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_4(closure$optionRow, closure$model, closure$optionId));
      td($receiver, optionsScreen$lambda$lambda$lambda$lambda$lambda$lambda_5(closure$optionRow));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda_8(closure$model) {
    return function ($receiver, optionRow) {
      var optionId = optionRow.optionId;
      tr($receiver, void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda$lambda_0(optionId, optionRow, closure$model));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_15(closure$model, closure$animate) {
    return function ($receiver) {
      $receiver.className = 'table table-striped table-hover table-condensed';
      thead($receiver, optionsScreen$lambda$lambda$lambda$lambda_7(closure$model));
      tbody($receiver, sortedWith(closure$model.optionRows, closure$model.currentSort), closure$animate ? new Collapse() : utils.NoEffect, optionsScreen$lambda$lambda$lambda$lambda_8(closure$model));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda_2(closure$model, closure$animate) {
    return function ($receiver) {
      addClass($receiver, ['container-fluid']);
      $receiver.className = 'table-responsive';
      table($receiver, optionsScreen$lambda$lambda$lambda_15(closure$model, closure$animate));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda_9(this$) {
    return function (it) {
      var tmp$;
      set_visible(this$, (tmp$ = it != null ? it.isEmpty() : null) != null ? tmp$ : true);
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda_16(closure$model) {
    return function ($receiver) {
      closure$model.options.onNext_qlkmfe$(optionsScreen$lambda$lambda$lambda$lambda_9($receiver));
      appendText($receiver, 'There are no options.');
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda_3(closure$model) {
    return function ($receiver) {
      col($receiver, new Col$Width$Xs(12), optionsScreen$lambda$lambda$lambda_16(closure$model));
      return Unit;
    };
  }
  function optionsScreen$lambda$lambda$lambda$lambda_10($receiver) {
    undoComponent($receiver);
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda_1(it) {
    window.location.hash = OptionModel$Companion_getInstance().toUrl_j97ou7$(null);
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda$lambda$lambda_2($receiver) {
    appendText($receiver, 'Add');
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda$lambda_11($receiver) {
    addClass($receiver, ['text-right']);
    btsButton($receiver, void 0, void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda$lambda_1, void 0, void 0, optionsScreen$lambda$lambda$lambda$lambda$lambda_2);
    return Unit;
  }
  function optionsScreen$lambda$lambda$lambda_17($receiver) {
    col($receiver, and(new Col$Width$Tn(9), new Col$Width$Xs(9)), optionsScreen$lambda$lambda$lambda$lambda_10);
    col($receiver, and(new Col$Width$Tn(3), new Col$Width$Xs(3)), optionsScreen$lambda$lambda$lambda$lambda_11);
    return Unit;
  }
  function optionsScreen$lambda$lambda_4($receiver) {
    row($receiver.navbarContainer, optionsScreen$lambda$lambda$lambda_17);
    return Unit;
  }
  function optionsScreen$lambda$lambda_5(closure$model) {
    return function (f, f_0) {
      closure$model.resort();
      return Unit;
    };
  }
  function optionsScreen$lambda(closure$model, closure$animate) {
    return function ($receiver) {
      inContext('buttonBar', optionsScreen$lambda$lambda(closure$model, $receiver));
      h4($receiver, optionsScreen$lambda$lambda_0);
      div($receiver, optionsScreen$lambda$lambda_1(closure$model));
      div($receiver, optionsScreen$lambda$lambda_2(closure$model, closure$animate));
      row($receiver, optionsScreen$lambda$lambda_3(closure$model));
      br($receiver);
      br($receiver);
      navbar($receiver, NavbarCompletePosition.FixedBottom, void 0, ContainerWidth.Fluid, optionsScreen$lambda$lambda_4);
      onChange(closure$model.effectiveTagCriteriaList, optionsScreen$lambda$lambda_5(closure$model));
      return Unit;
    };
  }
  function optionsScreen(model, animate) {
    if (animate === void 0)
      animate = false;
    return Div(optionsScreen$lambda(model, animate));
  }
  function editOnClick$lambda$lambda(closure$option) {
    return function (it) {
      UI_getInstance().optionModel.optionId.set_11rb$(closure$option.id);
      UI_getInstance().optionDialog.showDialog();
      return Unit;
    };
  }
  function editOnClick$lambda(closure$option, closure$render) {
    return function ($receiver) {
      $receiver.onclick = editOnClick$lambda$lambda(closure$option);
      closure$render($receiver, closure$option);
      return Unit;
    };
  }
  function editOnClick($receiver, option, render) {
    a($receiver, editOnClick$lambda(option, render));
  }
  function TagCriteriaModel(optionsModel, criteriaTagId) {
    if (criteriaTagId === void 0)
      criteriaTagId = toProperty(null);
    TagModel.call(this, criteriaTagId, optionsModel.dataSetId);
    this.optionsModel_0 = optionsModel;
    this.criteriaTagId = criteriaTagId;
    this.preferenceProperty = toProperty(false);
    this.criteriaTagId.onNext_qlkmfe$(TagCriteriaModel_init$lambda(this));
  }
  function TagCriteriaModel$getSelectableTagCriteriaValues$lambda(it) {
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = it.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (element.percentage === 100 || element.percentage === 0)
        destination.add_11rb$(element);
    }
    return destination;
  }
  TagCriteriaModel.prototype.getSelectableTagCriteriaValues = function () {
    return map(this.getSelectableTagValues(), TagCriteriaModel$getSelectableTagCriteriaValues$lambda);
  };
  TagCriteriaModel.prototype.getValue_apv9qp$ = function (tagId) {
    return this.optionsModel_0.getTagCriteriaValueProperty_apv9qp$(tagId);
  };
  TagCriteriaModel.prototype.setValue_iscb7r$ = function (tagId, newValue) {
    this.setValue_ifydeo$(tagId, newValue, this.preferenceProperty.get());
  };
  TagCriteriaModel.prototype.setValue_ifydeo$ = function (tagId, newValue, preference) {
    this.optionsModel_0.setTagCriteriaValue_ifydeo$(tagId, newValue, preference);
  };
  TagCriteriaModel.prototype.setValue_lwb4g3$ = function (newValue, preference) {
    this.setValue_ifydeo$(ensureNotNull(this.criteriaTagId.get()), newValue, preference);
  };
  function TagCriteriaModel_init$lambda(this$TagCriteriaModel) {
    return function (tagId) {
      var tmp$, tmp$_0;
      tmp$_0 = this$TagCriteriaModel.preferenceProperty;
      var tmp$_1;
      if (tagId != null) {
        var tmp$_2;
        tmp$_1 = (tmp$_2 = this$TagCriteriaModel.optionsModel_0.getTagCriteriaProperty_apv9qp$(tagId).get()) != null ? tmp$_2.preference : null;
      }
       else
        tmp$_1 = null;
      tmp$_0.set_11rb$((tmp$ = tmp$_1) != null ? tmp$ : false);
      return Unit;
    };
  }
  TagCriteriaModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagCriteriaModel',
    interfaces: [TagModel]
  };
  function buttonForTagCriteria$lambda(closure$tagId) {
    return function (it) {
      UI_getInstance().showTagCriteriaDialog_apv9qp$(closure$tagId);
      return Unit;
    };
  }
  function buttonForTagCriteria$lambda$lambda(this$) {
    return function (it) {
      this$.textContent = it != null ? it.label : null;
      return Unit;
    };
  }
  function buttonForTagCriteria$lambda_0(closure$value, closure$tag) {
    return function ($receiver) {
      setClassPresence_0($receiver, 'tag-true', closure$value, true);
      setClassPresence_0($receiver, 'tag-false', closure$value, false);
      setClassPresence_0($receiver, 'tag-null', closure$value, null);
      closure$tag.onNext_qlkmfe$(buttonForTagCriteria$lambda$lambda($receiver));
      return Unit;
    };
  }
  function buttonForTagCriteria($receiver, tagId, optionsModel) {
    var tag = findProperty(Factory_getInstance().tagRepository, tagId);
    var value = optionsModel.getTagCriteriaValueProperty_apv9qp$(tagId);
    btsButton($receiver, void 0, void 0, void 0, buttonForTagCriteria$lambda(tagId), void 0, void 0, buttonForTagCriteria$lambda_0(value, tag));
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      closure$model.label.onNext_qlkmfe$(tagCriteriaDialog$lambda$lambda$lambda$lambda($receiver));
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda_0(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      closure$model.quickSummary.onNext_qlkmfe$(tagCriteriaDialog$lambda$lambda$lambda$lambda_0($receiver));
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda(closure$model) {
    return function ($receiver) {
      h4($receiver, tagCriteriaDialog$lambda$lambda$lambda(closure$model));
      small($receiver, tagCriteriaDialog$lambda$lambda$lambda_0(closure$model));
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda_1(it) {
    return equals(it, 'Criteria');
  }
  function tagCriteriaDialog$lambda$lambda$lambda_2(it) {
    return equals(it, 'Preference');
  }
  function tagCriteriaDialog$lambda$lambda$lambda_3(it) {
    return equals(it, 'Tag');
  }
  function tagCriteriaDialog$lambda$lambda$lambda_4(closure$model) {
    return function (it) {
      if (it)
        closure$model.preferenceProperty.set_11rb$(false);
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda_5(closure$model) {
    return function (it) {
      if (it)
        closure$model.preferenceProperty.set_11rb$(true);
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda$lambda(closure$activeContextName) {
    return function (it) {
      closure$activeContextName.set_11rb$('Criteria');
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda(closure$activeContextName) {
    return function ($receiver) {
      appendText($receiver, 'Criteria');
      $receiver.onclick = tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda$lambda(closure$activeContextName);
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda_1(closure$activeContextName) {
    return function ($receiver) {
      a($receiver, tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda(closure$activeContextName));
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName) {
    return function (it) {
      closure$activeContextName.set_11rb$('Preference');
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName) {
    return function ($receiver) {
      appendText($receiver, 'Preference');
      $receiver.onclick = tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName);
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda_2(closure$activeContextName) {
    return function ($receiver) {
      a($receiver, tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName));
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$activeContextName) {
    return function (it) {
      closure$activeContextName.set_11rb$('Tag');
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda_1(closure$activeContextName) {
    return function ($receiver) {
      appendText($receiver, 'Edit Tag');
      $receiver.onclick = tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$activeContextName);
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda$lambda_3(closure$activeContextName) {
    return function ($receiver) {
      a($receiver, tagCriteriaDialog$lambda$lambda$lambda$lambda$lambda_1(closure$activeContextName));
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda_6(closure$criteriaTabActive, closure$activeContextName, closure$preferenceTabActive, closure$tagTabActive) {
    return function ($receiver) {
      $receiver.item_k4rdwa$(closure$criteriaTabActive, void 0, tagCriteriaDialog$lambda$lambda$lambda$lambda_1(closure$activeContextName));
      $receiver.item_k4rdwa$(closure$preferenceTabActive, void 0, tagCriteriaDialog$lambda$lambda$lambda$lambda_2(closure$activeContextName));
      $receiver.item_k4rdwa$(closure$tagTabActive, void 0, tagCriteriaDialog$lambda$lambda$lambda$lambda_3(closure$activeContextName));
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda_7(closure$model, closure$criteriaTabActive, closure$preferenceTabActive, closure$dialog, closure$tagTabActive) {
    return function ($receiver) {
      addClass($receiver, ['tab-content clearfix']);
      tagCriteriaTabsContent($receiver, closure$model, closure$criteriaTabActive, closure$preferenceTabActive, closure$dialog);
      tagTabContent($receiver, closure$model, closure$tagTabActive, closure$dialog);
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda_0(closure$model, closure$dialog) {
    return function ($receiver) {
      var activeContextName = new Property('Criteria');
      var criteriaTabActive = map(activeContextName, tagCriteriaDialog$lambda$lambda$lambda_1);
      var preferenceTabActive = map(activeContextName, tagCriteriaDialog$lambda$lambda$lambda_2);
      var tagTabActive = map(activeContextName, tagCriteriaDialog$lambda$lambda$lambda_3);
      criteriaTabActive.onNext_qlkmfe$(tagCriteriaDialog$lambda$lambda$lambda_4(closure$model));
      preferenceTabActive.onNext_qlkmfe$(tagCriteriaDialog$lambda$lambda$lambda_5(closure$model));
      navTabs($receiver, void 0, void 0, tagCriteriaDialog$lambda$lambda$lambda_6(criteriaTabActive, activeContextName, preferenceTabActive, tagTabActive));
      div($receiver, tagCriteriaDialog$lambda$lambda$lambda_7(closure$model, criteriaTabActive, preferenceTabActive, closure$dialog, tagTabActive));
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda_8(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda$lambda$lambda_9($receiver) {
    appendText($receiver, 'Close');
    return Unit;
  }
  function tagCriteriaDialog$lambda$lambda_1(closure$dialog) {
    return function ($receiver) {
      btsButton($receiver, void 0, void 0, void 0, tagCriteriaDialog$lambda$lambda$lambda_8(closure$dialog), void 0, void 0, tagCriteriaDialog$lambda$lambda$lambda_9);
      return Unit;
    };
  }
  function tagCriteriaDialog$lambda(closure$model) {
    return function ($receiver, dialog) {
      $receiver.header_htzy0l$(tagCriteriaDialog$lambda$lambda(closure$model));
      $receiver.body_iw61es$(tagCriteriaDialog$lambda$lambda_0(closure$model, dialog));
      $receiver.footer_iw61es$(tagCriteriaDialog$lambda$lambda_1(dialog));
      return Unit;
    };
  }
  function tagCriteriaDialog(model) {
    return prepareDialog(DialogSize.Default, tagCriteriaDialog$lambda(model));
  }
  function buttonToSetTagCriteriaValue$lambda(closure$model) {
    return function (it) {
      return findProperty(closure$model.tagRepository, it);
    };
  }
  function buttonToSetTagCriteriaValue$lambda_0(closure$model, closure$tagId, closure$newValue, closure$afterAction) {
    return function (event) {
      closure$model.setValue_iscb7r$(ensureNotNull(closure$tagId.get()), closure$newValue);
      closure$afterAction != null ? closure$afterAction(event) : null;
      return Unit;
    };
  }
  function buttonToSetTagCriteriaValue$lambda$lambda(closure$model) {
    return function (it) {
      return closure$model.getValue_apv9qp$(it);
    };
  }
  function buttonToSetTagCriteriaValue$lambda$lambda_0(closure$newValue, this$) {
    return function (it) {
      this$.textContent = closure$newValue != null ? it != null ? it.label : null : '(clear)';
      return Unit;
    };
  }
  function buttonToSetTagCriteriaValue$lambda_1(closure$newValue, closure$tagId, closure$model, closure$tag) {
    return function ($receiver) {
      addClass($receiver, ['tag-' + toString(closure$newValue)]);
      setClassPresence_0($receiver, 'btn-primary', flatMapIfNotNull(closure$tagId, buttonToSetTagCriteriaValue$lambda$lambda(closure$model)), closure$newValue);
      closure$tag.onNext_qlkmfe$(buttonToSetTagCriteriaValue$lambda$lambda_0(closure$newValue, $receiver));
      return Unit;
    };
  }
  function buttonToSetTagCriteriaValue($receiver, tagId, model, newValue, afterAction) {
    if (afterAction === void 0)
      afterAction = null;
    var tag = flatMapIfNotNull(tagId, buttonToSetTagCriteriaValue$lambda(model));
    btsButton($receiver, void 0, void 0, void 0, buttonToSetTagCriteriaValue$lambda_0(model, tagId, newValue, afterAction), void 0, void 0, buttonToSetTagCriteriaValue$lambda_1(newValue, tagId, model, tag));
  }
  function TagModel(tagId, dataSetId, tagRepository, tagTagRepository) {
    if (tagRepository === void 0)
      tagRepository = Factory_getInstance().tagRepository;
    if (tagTagRepository === void 0)
      tagTagRepository = Factory_getInstance().tagTagRepository;
    this.tagId = tagId;
    this.dataSetId = dataSetId;
    this.tagRepository = tagRepository;
    this.tagTagRepository_y6zw9g$_0 = tagTagRepository;
    this.alphabeticalTagIds_uj3h88$_0 = alphabeticalTagIds(this.tagRepository, this.dataSetId);
    this.tag = flatMapIfNotNull(this.tagId, TagModel$tag$lambda(this));
    this.label = mapAsDefault(this.tag, TagModel$label$lambda);
    this.quickSummary = mapAsDefault(this.tag, TagModel$quickSummary$lambda);
    this.validation = validate(this.label, 'Label is mandatory', TagModel$validation$lambda);
    this.areTagsExpanded = new Property(false);
  }
  TagModel.prototype.save = function () {
    var tmp$;
    var dataSetId = this.dataSetId.get();
    if (this.validation.get().success && dataSetId != null) {
      var updatedTag = new Tag(this.label.get(), dataSetId, this.quickSummary.get(), void 0, this.tagId.get());
      console.info('saveAndGetId with ' + updatedTag);
      this.tagRepository.save_tf6uce$(this.tag.get(), updatedTag);
      tmp$ = true;
    }
     else {
      tmp$ = false;
    }
    return tmp$;
  };
  TagModel.prototype.cancel = function () {
  };
  TagModel.prototype.deleteTag = function () {
    var tmp$;
    if ((tmp$ = this.tagId.get()) != null) {
      this.tagRepository.remove_83a66n$(tmp$);
    }
  };
  TagModel.prototype.toggleTagsExpanded = function () {
    this.areTagsExpanded.set_11rb$(!this.areTagsExpanded.get());
  };
  function TagModel$getSelectableTagValues$lambda(this$TagModel) {
    return function (tagId1) {
      if (tagId1 == null) {
        return emptySet();
      }
       else {
        var tag100 = new TagValue(tagId1, 100);
        var tag0 = new TagValue(tagId1, 0);
        var impliesForTrue = getTagValuesThatImplyTagValue(this$TagModel.tagTagRepository_y6zw9g$_0, tag100);
        var impliesForFalse = getTagValuesThatImplyTagValue(this$TagModel.tagTagRepository_y6zw9g$_0, tag0);
        return plus_0(plus_0(setOf([tag100, new TagValue(tagId1, 80), new TagValue(tagId1, 50), new TagValue(tagId1, 20), tag0]), impliesForTrue), impliesForFalse);
      }
    };
  }
  TagModel.prototype.getSelectableTagValues = function () {
    return map(this.tagId, TagModel$getSelectableTagValues$lambda(this));
  };
  function TagModel$getTagTagIdPairs$lambda$lambda(primaryTagIds, tagId) {
    var tmp$;
    if (tagId != null) {
      var destination = ArrayList_init();
      var tmp$_0;
      tmp$_0 = primaryTagIds.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        if (!equals(element, tagId))
          destination.add_11rb$(element);
      }
      var destination_0 = ArrayList_init(collectionSizeOrDefault(destination, 10));
      var tmp$_1;
      tmp$_1 = destination.iterator();
      while (tmp$_1.hasNext()) {
        var item = tmp$_1.next();
        destination_0.add_11rb$(new TagTagIdPair(tagId, item));
      }
      tmp$ = destination_0;
    }
     else
      tmp$ = null;
    return tmp$;
  }
  function TagModel$getTagTagIdPairs$lambda$lambda_0(this$TagModel) {
    return function (it) {
      return listProperty_0(this$TagModel.tagTagRepository_y6zw9g$_0, TagTagIdPairField_getInstance(), new TagTagByPrimaryTagId(it));
    };
  }
  function TagModel$getTagTagIdPairs$lambda(this$TagModel, closure$tagIdProperty) {
    return function (expanded) {
      if (expanded) {
        return mapWith(this$TagModel.alphabeticalTagIds_uj3h88$_0, closure$tagIdProperty, TagModel$getTagTagIdPairs$lambda$lambda);
      }
       else {
        return flatMapIfNotNull(closure$tagIdProperty, TagModel$getTagTagIdPairs$lambda$lambda_0(this$TagModel));
      }
    };
  }
  function TagModel$getTagTagIdPairs$lambda_0(it) {
    return it != null ? it : emptyList();
  }
  TagModel.prototype.getTagTagIdPairs_26ka6p$ = function (tagIdProperty) {
    return map(flatMap(this.areTagsExpanded, TagModel$getTagTagIdPairs$lambda(this, tagIdProperty)), TagModel$getTagTagIdPairs$lambda_0);
  };
  TagModel.prototype.saveNewTagTag_z8kscp$ = function (primaryTagId, newTagLabel, htmlInputElement, event, newPercentage) {
    if (newPercentage === void 0)
      newPercentage = 100;
    var newLabel = newTagLabel.get();
    var dataSetIdValue = this.dataSetId.get();
    if (!isBlank(newLabel) && dataSetIdValue != null) {
      var newTagId = findOrCreate(this.tagRepository, newLabel, dataSetIdValue);
      if (primaryTagId != null) {
        setValue(this.tagTagRepository_y6zw9g$_0, new TagTag(primaryTagId, newTagId, newPercentage, dataSetIdValue));
      }
      event.preventDefault();
      htmlInputElement.focus();
    }
    newTagLabel.set_11rb$('');
  };
  function TagModel$tag$lambda(this$TagModel) {
    return function (it) {
      return findProperty(this$TagModel.tagRepository, it);
    };
  }
  function TagModel$label$lambda(it) {
    var tmp$;
    return (tmp$ = it != null ? it.label : null) != null ? tmp$ : '';
  }
  function TagModel$quickSummary$lambda(it) {
    var tmp$;
    return (tmp$ = it != null ? it.quickSummary : null) != null ? tmp$ : '';
  }
  function TagModel$validation$lambda(it) {
    return it.length > 0;
  }
  TagModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagModel',
    interfaces: []
  };
  function tagEditForm$lambda$lambda(it) {
    return toState(it);
  }
  function tagEditForm$lambda$lambda$lambda$lambda(closure$model) {
    return function (it) {
      return closure$model.save();
    };
  }
  function tagEditForm$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      $receiver.placeholder = 'Label';
      $receiver.size = 30;
      $receiver.onblur = tagEditForm$lambda$lambda$lambda$lambda(closure$model);
      return Unit;
    };
  }
  function tagEditForm$lambda$lambda_0(closure$model) {
    return function ($receiver, it) {
      textInput($receiver, closure$model.label, void 0, void 0, void 0, void 0, tagEditForm$lambda$lambda$lambda(closure$model));
      return Unit;
    };
  }
  function tagEditForm$lambda$lambda$lambda$lambda_0(closure$model) {
    return function (it) {
      return closure$model.save();
    };
  }
  function tagEditForm$lambda$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      $receiver.placeholder = 'Quick Summary';
      $receiver.size = 40;
      $receiver.onblur = tagEditForm$lambda$lambda$lambda$lambda_0(closure$model);
      return Unit;
    };
  }
  function tagEditForm$lambda$lambda_1(closure$model) {
    return function ($receiver, it) {
      textInput($receiver, closure$model.quickSummary, void 0, void 0, void 0, void 0, tagEditForm$lambda$lambda$lambda_0(closure$model));
      return Unit;
    };
  }
  function tagEditForm$lambda(closure$model) {
    return function ($receiver) {
      $receiver.btsFormItemSimple_ekuevp$(void 0, map(closure$model.validation, tagEditForm$lambda$lambda), 'Tag:', tagEditForm$lambda$lambda_0(closure$model));
      $receiver.btsFormItemSimple_ekuevp$(void 0, void 0, '', tagEditForm$lambda$lambda_1(closure$model));
      return Unit;
    };
  }
  function tagEditForm$lambda$lambda_2($receiver) {
    $receiver.textContent = 'Implied Tags';
    return Unit;
  }
  function tagEditForm$lambda$lambda_3($receiver, tagTagIdPair) {
    buttonForTagTag($receiver, tagTagIdPair);
    return Unit;
  }
  function tagEditForm$lambda$lambda_4(closure$model, closure$input) {
    return function (it) {
      closure$model.toggleTagsExpanded();
      ensureNotNull(closure$input.v).focus();
      return Unit;
    };
  }
  function tagEditForm$lambda$lambda$lambda_1(this$) {
    return function (it) {
      this$.textContent = it ? '<<' : '...';
      return Unit;
    };
  }
  function tagEditForm$lambda$lambda_5(closure$model) {
    return function ($receiver) {
      closure$model.areTagsExpanded.onNext_qlkmfe$(tagEditForm$lambda$lambda$lambda_1($receiver));
      return Unit;
    };
  }
  function tagEditForm$lambda$lambda$lambda_2(closure$model, closure$newTagLabel, this$) {
    return function (event) {
      closure$model.saveNewTagTag_z8kscp$(closure$model.tagId.get(), closure$newTagLabel, this$, event);
      return Unit;
    };
  }
  function tagEditForm$lambda$lambda_6(closure$model, closure$newTagLabel) {
    return function ($receiver) {
      addClass($receiver, ['form-control']);
      $receiver.placeholder = 'New Implied Tag';
      $receiver.size = 15;
      $receiver.onblur = tagEditForm$lambda$lambda$lambda_2(closure$model, closure$newTagLabel, $receiver);
      return Unit;
    };
  }
  function tagEditForm$lambda_0(closure$model) {
    return function ($receiver) {
      $receiver.className = 'text-left';
      h4($receiver, tagEditForm$lambda$lambda_2);
      repeatLive($receiver, closure$model.getTagTagIdPairs_26ka6p$(closure$model.tagId), void 0, tagEditForm$lambda$lambda_3);
      var newTagLabel = new Property('');
      var input = {v: null};
      btsButton($receiver, void 0, void 0, void 0, tagEditForm$lambda$lambda_4(closure$model, input), void 0, void 0, tagEditForm$lambda$lambda_5(closure$model));
      input.v = textInput($receiver, newTagLabel, void 0, void 0, void 0, void 0, tagEditForm$lambda$lambda_6(closure$model, newTagLabel));
      return Unit;
    };
  }
  function tagEditForm($receiver, model) {
    btsFormDefault($receiver, tagEditForm$lambda(model));
    div($receiver, tagEditForm$lambda_0(model));
  }
  function TagTagModel(dataSetId, tagTagIdPair, repository) {
    if (tagTagIdPair === void 0)
      tagTagIdPair = toProperty(null);
    if (repository === void 0)
      repository = Factory_getInstance().tagTagRepository;
    TagValueModel.call(this, map(tagTagIdPair, TagTagModel_init$lambda), dataSetId);
    this.tagTagIdPair = tagTagIdPair;
    this.repository = repository;
    this.primaryTagId = map(this.tagTagIdPair, TagTagModel$primaryTagId$lambda);
    this.primaryTag = flatMapIfNotNull(this.primaryTagId, TagTagModel$primaryTag$lambda(this));
  }
  function TagTagModel$getPercentage$lambda$lambda(it) {
    return it.percentage;
  }
  function TagTagModel$getPercentage$lambda(this$TagTagModel, closure$tagId) {
    return function (it) {
      return mapIfNotNull(findFirstOrNullProperty(this$TagTagModel.repository, new TagTagByTagTagIdPair(new TagTagIdPair(it, closure$tagId))), void 0, TagTagModel$getPercentage$lambda$lambda);
    };
  }
  TagTagModel.prototype.getPercentage_apv9qp$ = function (tagId) {
    return flatMapIfNotNull(this.primaryTagId, TagTagModel$getPercentage$lambda(this, tagId));
  };
  function TagTagModel$setPercentage$lambda(this$TagTagModel, closure$tagId, closure$newPercentage) {
    return function () {
      var primaryTagId = ensureNotNull(ensureNotNull(this$TagTagModel.primaryTag.get()).id);
      var tagTagIdPair = new TagTagIdPair(primaryTagId, closure$tagId);
      if (closure$newPercentage == null) {
        remove_0(this$TagTagModel.repository, tagTagIdPair);
      }
       else {
        setValue(this$TagTagModel.repository, new TagTag(primaryTagId, closure$tagId, closure$newPercentage, ensureNotNull(this$TagTagModel.dataSetId.get())));
      }
      if (!equals(closure$tagId, this$TagTagModel.tagId.get())) {
        remove_0(this$TagTagModel.repository, ensureNotNull(this$TagTagModel.tagTagIdPair.get()));
      }
      return Unit;
    };
  }
  TagTagModel.prototype.setPercentage_q0ln5l$ = function (tagId, newPercentage) {
    var tmp$, tmp$_0;
    UndoComponent_getInstance().undoable_u8di54$('Marked ' + toString((tmp$ = this.primaryTag.get()) != null ? tmp$.label : null) + ' as implying ' + this.label + '=' + toString(newPercentage), 'Unmarked ' + toString((tmp$_0 = this.primaryTag.get()) != null ? tmp$_0.label : null) + ' as implying ' + this.label + '=' + toString(newPercentage), TagTagModel$setPercentage$lambda(this, tagId, newPercentage));
  };
  function TagTagModel_init$lambda(it) {
    return it != null ? it.tagId : null;
  }
  function TagTagModel$primaryTagId$lambda(it) {
    return it != null ? it.primaryTagId : null;
  }
  function TagTagModel$primaryTag$lambda(this$TagTagModel) {
    return function (it) {
      return findProperty(this$TagTagModel.tagRepository, it);
    };
  }
  TagTagModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagTagModel',
    interfaces: [TagValueModel]
  };
  function buttonForTagTag$lambda(closure$tagTagIdPair) {
    return function (it) {
      UI_getInstance().showDialog_42n4bc$(closure$tagTagIdPair);
      return Unit;
    };
  }
  function buttonForTagTag($receiver, tagTagIdPair, tag) {
    if (tag === void 0)
      tag = findProperty(Factory_getInstance().tagRepository, tagTagIdPair.tagId);
    var percentage = findPercentageProperty_0(Factory_getInstance().tagTagRepository, tagTagIdPair);
    buttonForTagValue($receiver, tag, percentage, buttonForTagTag$lambda(tagTagIdPair));
  }
  function tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      this$.textContent = it != null ? it.label : null;
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      closure$model.primaryTag.onNext_qlkmfe$(tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda($receiver));
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      span($receiver, tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda(closure$model));
      appendText($receiver, ' implies:');
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      h4($receiver, tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda(closure$model));
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$model, closure$dialog) {
    return function ($receiver) {
      $receiver.className = 'btn-toolbar';
      buttonToSetTagValue($receiver, closure$model.tagId, closure$model, 100, tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$dialog));
      buttonToSetTagValue($receiver, closure$model.tagId, closure$model, null, tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda_1(closure$dialog));
      buttonToSetTagValue($receiver, closure$model.tagId, closure$model, 0, tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda$lambda_2(closure$dialog));
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda$lambda$lambda_0(closure$model, closure$dialog) {
    return function ($receiver) {
      div($receiver, tagTagDialog$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$model, closure$dialog));
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda$lambda(closure$model, closure$dialog) {
    return function ($receiver) {
      $receiver.btsFormLabel_hrwec9$(tagTagDialog$lambda$lambda$lambda$lambda$lambda(closure$model));
      $receiver.btsFormInput_iw61es$(tagTagDialog$lambda$lambda$lambda$lambda$lambda_0(closure$model, closure$dialog));
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda(closure$model, closure$dialog) {
    return function ($receiver) {
      $receiver.btsFormItem_1wd1uy$(void 0, void 0, tagTagDialog$lambda$lambda$lambda$lambda(closure$model, closure$dialog));
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda(closure$model, closure$dialog) {
    return function ($receiver) {
      btsFormDefault($receiver, tagTagDialog$lambda$lambda$lambda(closure$model, closure$dialog));
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda_0(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagTagDialog$lambda$lambda$lambda_1($receiver) {
    appendText($receiver, 'Close');
    return Unit;
  }
  function tagTagDialog$lambda$lambda_0(closure$dialog) {
    return function ($receiver) {
      btsButton($receiver, void 0, void 0, void 0, tagTagDialog$lambda$lambda$lambda_0(closure$dialog), void 0, void 0, tagTagDialog$lambda$lambda$lambda_1);
      return Unit;
    };
  }
  function tagTagDialog$lambda(closure$model) {
    return function ($receiver, dialog) {
      $receiver.body_iw61es$(tagTagDialog$lambda$lambda(closure$model, dialog));
      $receiver.footer_iw61es$(tagTagDialog$lambda$lambda_0(dialog));
      return Unit;
    };
  }
  function tagTagDialog(model) {
    return prepareDialog(DialogSize.Default, tagTagDialog$lambda(model));
  }
  function TagValueForOptionLabelModel(dataSetId, optionLabel, tagValues, tagIdProperty) {
    if (tagIdProperty === void 0)
      tagIdProperty = new Property(null);
    TagValueModel.call(this, tagIdProperty, dataSetId);
    this.optionLabel = optionLabel;
    this.tagValues = tagValues;
    this.tagIdProperty = tagIdProperty;
    this.percentage = new Property(null);
  }
  TagValueForOptionLabelModel.prototype.getPercentage_apv9qp$ = function (tagId) {
    return getPercentage(this.tagValues, tagId);
  };
  TagValueForOptionLabelModel.prototype.setPercentage_q0ln5l$ = function (tagId, newPercentage) {
    setPercentage(this.tagValues, tagId, newPercentage);
  };
  TagValueForOptionLabelModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagValueForOptionLabelModel',
    interfaces: [TagValueModel]
  };
  function getPercentage$lambda(closure$tagId) {
    return function (it) {
      var tmp$;
      var firstOrNull$result;
      firstOrNull$break: do {
        var tmp$_0;
        tmp$_0 = it.iterator();
        while (tmp$_0.hasNext()) {
          var element = tmp$_0.next();
          var closure$tagId_0 = closure$tagId;
          var tmp$_1;
          if ((tmp$_1 = element.tagId) != null ? tmp$_1.equals(closure$tagId_0) : null) {
            firstOrNull$result = element;
            break firstOrNull$break;
          }
        }
        firstOrNull$result = null;
      }
       while (false);
      return (tmp$ = firstOrNull$result) != null ? tmp$.percentage : null;
    };
  }
  function getPercentage($receiver, tagId) {
    return map($receiver, getPercentage$lambda(tagId));
  }
  function setPercentage$lambda$lambda(closure$tagId) {
    return function (it) {
      var tmp$;
      return (tmp$ = it.tagId) != null ? tmp$.equals(closure$tagId) : null;
    };
  }
  function setPercentage$lambda(closure$tagId, closure$newPercentage) {
    return function (list) {
      removeAll(list, setPercentage$lambda$lambda(closure$tagId));
      if (closure$newPercentage != null) {
        list.add_11rb$(new TagValue(closure$tagId, closure$newPercentage));
      }
      return Unit;
    };
  }
  function setPercentage($receiver, tagId, newPercentage) {
    modifyList($receiver, setPercentage$lambda(tagId, newPercentage));
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda(closure$model) {
    return function ($receiver) {
      closure$model.label.onNext_qlkmfe$(tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda($receiver));
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda_0(this$) {
    return function (it) {
      this$.textContent = it;
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda_0(closure$model) {
    return function ($receiver) {
      closure$model.quickSummary.onNext_qlkmfe$(tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda_0($receiver));
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda(closure$model) {
    return function ($receiver) {
      h4($receiver, tagValueForOptionLabelDialog$lambda$lambda$lambda(closure$model));
      small($receiver, tagValueForOptionLabelDialog$lambda$lambda$lambda_0(closure$model));
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda_1(it) {
    return equals(it, 'Option');
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda_2(it) {
    return equals(it, 'Tag');
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda$lambda$lambda(closure$activeContextName) {
    return function (it) {
      closure$activeContextName.set_11rb$('Option');
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda$lambda(closure$activeContextName) {
    return function ($receiver) {
      appendText($receiver, 'Option');
      $receiver.onclick = tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda$lambda$lambda(closure$activeContextName);
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda_1(closure$activeContextName) {
    return function ($receiver) {
      a($receiver, tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda$lambda(closure$activeContextName));
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName) {
    return function (it) {
      closure$activeContextName.set_11rb$('Tag');
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName) {
    return function ($receiver) {
      appendText($receiver, 'Edit Tag');
      $receiver.onclick = tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName);
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda_2(closure$activeContextName) {
    return function ($receiver) {
      a($receiver, tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda$lambda_0(closure$activeContextName));
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda_3(closure$optionTabActive, closure$activeContextName, closure$tagTabActive) {
    return function ($receiver) {
      $receiver.item_k4rdwa$(closure$optionTabActive, void 0, tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda_1(closure$activeContextName));
      $receiver.item_k4rdwa$(closure$tagTabActive, void 0, tagValueForOptionLabelDialog$lambda$lambda$lambda$lambda_2(closure$activeContextName));
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda_4(closure$model, closure$optionTabActive, closure$dialog, closure$tagTabActive) {
    return function ($receiver) {
      addClass($receiver, ['tab-content clearfix']);
      optionTagTabContent($receiver, closure$model, closure$model.optionLabel, closure$model.getSelectableTagValues(), closure$optionTabActive, closure$dialog);
      tagTabContent($receiver, closure$model, closure$tagTabActive, closure$dialog);
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda_0(closure$model, closure$dialog) {
    return function ($receiver) {
      var activeContextName = new Property('Option');
      var optionTabActive = map(activeContextName, tagValueForOptionLabelDialog$lambda$lambda$lambda_1);
      var tagTabActive = map(activeContextName, tagValueForOptionLabelDialog$lambda$lambda$lambda_2);
      navTabs($receiver, void 0, void 0, tagValueForOptionLabelDialog$lambda$lambda$lambda_3(optionTabActive, activeContextName, tagTabActive));
      div($receiver, tagValueForOptionLabelDialog$lambda$lambda$lambda_4(closure$model, optionTabActive, closure$dialog, tagTabActive));
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda_5(closure$dialog) {
    return function (it) {
      closure$dialog.hideDialog();
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda$lambda$lambda_6($receiver) {
    appendText($receiver, 'Close');
    return Unit;
  }
  function tagValueForOptionLabelDialog$lambda$lambda_1(closure$dialog) {
    return function ($receiver) {
      btsButton($receiver, void 0, void 0, void 0, tagValueForOptionLabelDialog$lambda$lambda$lambda_5(closure$dialog), void 0, void 0, tagValueForOptionLabelDialog$lambda$lambda$lambda_6);
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog$lambda(closure$model) {
    return function ($receiver, dialog) {
      $receiver.header_htzy0l$(tagValueForOptionLabelDialog$lambda$lambda(closure$model));
      $receiver.body_iw61es$(tagValueForOptionLabelDialog$lambda$lambda_0(closure$model, dialog));
      $receiver.footer_iw61es$(tagValueForOptionLabelDialog$lambda$lambda_1(dialog));
      return Unit;
    };
  }
  function tagValueForOptionLabelDialog(model) {
    return prepareDialog(DialogSize.Default, tagValueForOptionLabelDialog$lambda(model));
  }
  function TagValueModel(tagId, dataSetId, tagRepository, tagTagRepository) {
    if (tagRepository === void 0)
      tagRepository = Factory_getInstance().tagRepository;
    if (tagTagRepository === void 0)
      tagTagRepository = Factory_getInstance().tagTagRepository;
    TagModel.call(this, tagId, dataSetId, tagRepository, tagTagRepository);
  }
  TagValueModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagValueModel',
    interfaces: [TagModel]
  };
  function buttonForTagValue$lambda$lambda(this$) {
    return function (f) {
      var tag = f.component1()
      , percentage = f.component2();
      this$.textContent = tag != null ? tag.getDisplayLabel_mz3mdy$(percentage) : null;
      return Unit;
    };
  }
  function buttonForTagValue$lambda(closure$percentage, closure$tag) {
    return function ($receiver) {
      setClassPresence_0($receiver, 'tag-100', closure$percentage, 100);
      setClassPresence_0($receiver, 'tag-0', closure$percentage, 0);
      setClassPresence_0($receiver, 'tag-null', closure$percentage, null);
      zip(closure$tag, closure$percentage).onNext_qlkmfe$(buttonForTagValue$lambda$lambda($receiver));
      return Unit;
    };
  }
  function buttonForTagValue($receiver, tag, percentage, onclick) {
    btsButton($receiver, void 0, void 0, void 0, onclick, void 0, void 0, buttonForTagValue$lambda(percentage, tag));
  }
  function buttonToSetTagValue$lambda(closure$model) {
    return function (it) {
      return findProperty(closure$model.tagRepository, it);
    };
  }
  function buttonToSetTagValue$lambda_0(closure$model, closure$tagId, closure$newPercentage, closure$afterAction) {
    return function (event) {
      closure$model.setPercentage_q0ln5l$(ensureNotNull(closure$tagId.get()), closure$newPercentage);
      closure$afterAction != null ? closure$afterAction(event) : null;
      return Unit;
    };
  }
  function buttonToSetTagValue$lambda$lambda(closure$model) {
    return function (it) {
      return closure$model.getPercentage_apv9qp$(it);
    };
  }
  function buttonToSetTagValue$lambda$lambda_0(closure$newPercentage, this$) {
    return function (it) {
      this$.textContent = closure$newPercentage != null ? it != null ? it.getDisplayLabel_mz3mdy$(closure$newPercentage) : null : '(clear)';
      return Unit;
    };
  }
  function buttonToSetTagValue$lambda_1(closure$newPercentage, closure$tagId, closure$model, closure$tag) {
    return function ($receiver) {
      addClass($receiver, ['tag-' + toString(closure$newPercentage)]);
      setClassPresence_0($receiver, 'btn-primary', flatMapIfNotNull(closure$tagId, buttonToSetTagValue$lambda$lambda(closure$model)), closure$newPercentage);
      closure$tag.onNext_qlkmfe$(buttonToSetTagValue$lambda$lambda_0(closure$newPercentage, $receiver));
      return Unit;
    };
  }
  function buttonToSetTagValue($receiver, tagId, model, newPercentage, afterAction) {
    if (afterAction === void 0)
      afterAction = null;
    var tag = flatMapIfNotNull(tagId, buttonToSetTagValue$lambda(model));
    btsButton($receiver, void 0, void 0, void 0, buttonToSetTagValue$lambda_0(model, tagId, newPercentage, afterAction), void 0, void 0, buttonToSetTagValue$lambda_1(newPercentage, tagId, model, tag));
  }
  function FileBackupComponent() {
    FileBackupComponent_instance = this;
    this.backupDateFormat_0 = format(FileBackupComponent$backupDateFormat$lambda);
  }
  var addAll = Kotlin.kotlin.collections.addAll_ipc267$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  FileBackupComponent.prototype.getBackupDataAsString_0 = function () {
    var $receiver = Factory_getInstance().allRepositories;
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var list = element.localStorageKeys;
      addAll(destination, list);
    }
    var destination_0 = ArrayList_init(collectionSizeOrDefault(destination, 10));
    var tmp$_0;
    tmp$_0 = destination.iterator();
    while (tmp$_0.hasNext()) {
      var item = tmp$_0.next();
      var tmp$_1;
      destination_0.add_11rb$(new Pair(item, (tmp$_1 = localStorage[item]) != null ? JSON.parse(tmp$_1) : null));
    }
    var backupItems = copyToArray(destination_0);
    return JSON.stringify(json(backupItems.slice()));
  };
  FileBackupComponent.prototype.initializeData_za3rmp$ = function (initialData) {
    this.initializeDataFromJson_za3rmp$(typeof initialData === 'string' ? JSON.parse(initialData) : initialData);
  };
  FileBackupComponent.prototype.initializeDataFromJson_za3rmp$ = function (initialDataJson) {
    var $receiver = Factory_getInstance().allRepositories;
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (Kotlin.isType(element, LocalStorageRepository))
        destination.add_11rb$(element);
    }
    var tmp$_0;
    tmp$_0 = destination.iterator();
    while (tmp$_0.hasNext()) {
      var element_0 = tmp$_0.next();
      var entities = initialDataJson[element_0.localStorageKey];
      if (entities != null) {
        element_0.replaceAll_tfr4my$(entities);
      }
    }
  };
  function FileBackupComponent$createBackup$lambda$lambda(error) {
    handleError(new RuntimeException('error ' + error.code));
    return Unit;
  }
  function FileBackupComponent$createBackup$lambda$lambda$lambda$ObjectLiteral() {
  }
  FileBackupComponent$createBackup$lambda$lambda$lambda$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: []
  };
  function FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda(it) {
    window.alert('writer.error');
    return Unit;
  }
  function FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$fileEntry) {
    return function (it) {
      window.alert('Backup created: ' + closure$fileEntry.fullPath);
      return Unit;
    };
  }
  function FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda$lambda$lambda(closure$writer, closure$fileEntry, closure$backupData) {
    return function () {
      closure$writer.onerror = FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda;
      closure$writer.onwriteend = FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda$lambda$lambda$lambda_0(closure$fileEntry);
      var tmp$ = [closure$backupData];
      var type = 'application/json';
      var o = {};
      o['type'] = type;
      var dataObj = new Blob(tmp$, o);
      closure$writer.write(dataObj);
      return Unit;
    };
  }
  function FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda$lambda(closure$fileEntry, closure$backupData) {
    return function (writer) {
      handlingErrors('createBackup/writer', FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda$lambda$lambda(writer, closure$fileEntry, closure$backupData));
      return Unit;
    };
  }
  function FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda(closure$fileEntry, closure$backupData, closure$handleError) {
    return function () {
      closure$fileEntry.createWriter(FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda$lambda(closure$fileEntry, closure$backupData), closure$handleError);
      return Unit;
    };
  }
  function FileBackupComponent$createBackup$lambda$lambda$lambda$lambda(closure$backupData, closure$handleError) {
    return function (fileEntry) {
      handlingErrors('createBackup/fileEntry', FileBackupComponent$createBackup$lambda$lambda$lambda$lambda$lambda(fileEntry, closure$backupData, closure$handleError));
      return Unit;
    };
  }
  function FileBackupComponent$createBackup$lambda$lambda$lambda(closure$directoryEntry, this$FileBackupComponent, closure$backupData, closure$handleError) {
    return function () {
      var tmp$;
      var flags = new FileBackupComponent$createBackup$lambda$lambda$lambda$ObjectLiteral();
      flags.create = true;
      var directory = Kotlin.isType(tmp$ = closure$directoryEntry, Object) ? tmp$ : throwCCE();
      directory.getFile(appFilenamePrefix + '-' + Moment.Companion.now().format_7fp3go$(this$FileBackupComponent.backupDateFormat_0) + '.json', flags, FileBackupComponent$createBackup$lambda$lambda$lambda$lambda(closure$backupData, closure$handleError), closure$handleError);
      return Unit;
    };
  }
  function FileBackupComponent$createBackup$lambda$lambda_0(this$FileBackupComponent, closure$backupData, closure$handleError) {
    return function (directoryEntry) {
      handlingErrors('createBackup/directoryEntry', FileBackupComponent$createBackup$lambda$lambda$lambda(directoryEntry, this$FileBackupComponent, closure$backupData, closure$handleError));
      return Unit;
    };
  }
  function FileBackupComponent$createBackup$lambda(this$FileBackupComponent) {
    return function () {
      var tmp$, tmp$_0, tmp$_1;
      var cordova = get_cordova(window);
      var backupData = this$FileBackupComponent.getBackupDataAsString_0();
      if (cordova != null) {
        var handleError = FileBackupComponent$createBackup$lambda$lambda;
        tmp$_0 = (tmp$ = get_file(cordova).documentsDirectory) != null ? tmp$ : get_file(cordova).externalDataDirectory;
        tmp$_1 = FileBackupComponent$createBackup$lambda$lambda_0(this$FileBackupComponent, backupData, handleError);
        resolveLocalFileSystemURL(window, tmp$_0, tmp$_1, handleError);
      }
       else {
        console.info(backupData);
        window.alert("Backup can be found in the brower's console.");
      }
      return Unit;
    };
  }
  FileBackupComponent.prototype.createBackup = function () {
    handlingErrors('createBackup', FileBackupComponent$createBackup$lambda(this));
  };
  function FileBackupComponent$backupButton$lambda(this$FileBackupComponent) {
    return function (it) {
      this$FileBackupComponent.createBackup();
      return Unit;
    };
  }
  function FileBackupComponent$backupButton$lambda$lambda($receiver) {
    addClass($receiver, ['hidden-tn']);
    nbsp($receiver);
    appendText($receiver, 'Data');
    return Unit;
  }
  function FileBackupComponent$backupButton$lambda_0($receiver) {
    appendText($receiver, 'Backup');
    span($receiver, FileBackupComponent$backupButton$lambda$lambda);
    return Unit;
  }
  FileBackupComponent.prototype.backupButton_y4uc6z$ = function ($receiver) {
    btsButton($receiver, void 0, void 0, void 0, FileBackupComponent$backupButton$lambda(this), void 0, void 0, FileBackupComponent$backupButton$lambda_0);
  };
  function FileBackupComponent$backupDateFormat$lambda($receiver) {
    return $receiver.year.fourDigits.plus_61zpoe$('-').plus_dsxjyj$($receiver.month.twoDigits).plus_61zpoe$('-').plus_dsxjyj$($receiver.dayOfMonth.twoDigits).plus_61zpoe$('-').plus_dsxjyj$($receiver.hour24.twoDigits).plus_61zpoe$('-').plus_dsxjyj$($receiver.minutes.twoDigits).plus_61zpoe$('-').plus_dsxjyj$($receiver.seconds.twoDigits);
  }
  FileBackupComponent.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'FileBackupComponent',
    interfaces: []
  };
  var FileBackupComponent_instance = null;
  function FileBackupComponent_getInstance() {
    if (FileBackupComponent_instance === null) {
      new FileBackupComponent();
    }
    return FileBackupComponent_instance;
  }
  function flaticon$lambda(closure$icon) {
    return function ($receiver) {
      $receiver.className = 'flaticon flaticon-' + closure$icon;
      return Unit;
    };
  }
  function flaticon($receiver, icon) {
    return span($receiver, flaticon$lambda(icon));
  }
  function get_visible($receiver) {
    return !hasClass($receiver, 'hidden');
  }
  function set_visible($receiver, value) {
    if (value !== get_visible($receiver)) {
      if (value) {
        removeClass($receiver, ['hidden']);
      }
       else {
        addClass($receiver, ['hidden']);
      }
    }
  }
  var IllegalStateException_init = Kotlin.kotlin.IllegalStateException;
  function get_responsiveWidth($receiver) {
    throw new IllegalStateException_init('not implemented'.toString());
  }
  function set_responsiveWidth($receiver, value) {
    addClass($receiver, [value.css]);
  }
  function UndoComponent() {
    UndoComponent_instance = this;
    this.commandRecorder_0 = NormalCommandRecorder_getInstance();
    this.undoCommands_0 = toProperty(emptyList());
    this.redoCommands_0 = toProperty(emptyList());
  }
  Object.defineProperty(UndoComponent.prototype, 'undoCount', {
    get: function () {
      return this.undoCommands_0.get().size;
    }
  });
  Object.defineProperty(UndoComponent.prototype, 'redoCount', {
    get: function () {
      return this.redoCommands_0.get().size;
    }
  });
  function UndoComponent$undo$lambda(this$UndoComponent) {
    return function () {
      var undoCommandsCopy = this$UndoComponent.undoCommands_0.get();
      var index = undoCommandsCopy.size - 1 | 0;
      var commandToUndo = undoCommandsCopy.get_za3lpa$(index);
      var redoCommand = commandToUndo.executeAndGetOpposite();
      console.info(commandToUndo.toString() + ' (undoing: ' + commandToUndo + ')');
      removeAt(this$UndoComponent.undoCommands_0, index);
      add(this$UndoComponent.redoCommands_0, redoCommand);
      return Unit;
    };
  }
  UndoComponent.prototype.undo = function () {
    this.notUndoable_klfg04$(UndoComponent$undo$lambda(this));
  };
  function UndoComponent$redo$lambda(this$UndoComponent) {
    return function () {
      var redoCommandsCopy = this$UndoComponent.redoCommands_0.get();
      var index = redoCommandsCopy.size - 1 | 0;
      var commandToRedo = redoCommandsCopy.get_za3lpa$(index);
      var undoCommand = commandToRedo.executeAndGetOpposite();
      console.info(commandToRedo.toString() + ' (redo)');
      removeAt(this$UndoComponent.redoCommands_0, index);
      add(this$UndoComponent.undoCommands_0, undoCommand);
      return Unit;
    };
  }
  UndoComponent.prototype.redo = function () {
    this.notUndoable_klfg04$(UndoComponent$redo$lambda(this));
  };
  function UndoComponent$render$lambda$lambda(this$UndoComponent) {
    return function (it) {
      this$UndoComponent.undo();
      return Unit;
    };
  }
  function UndoComponent$render$lambda$lambda$lambda(this$) {
    return function (it) {
      set_visible(this$, !it.isEmpty());
      this$.disabled = it.isEmpty();
      return Unit;
    };
  }
  function UndoComponent$render$lambda$lambda_0(this$UndoComponent) {
    return function ($receiver) {
      this$UndoComponent.undoCommands_0.onNext_qlkmfe$(UndoComponent$render$lambda$lambda$lambda($receiver));
      appendText($receiver, 'Undo');
      return Unit;
    };
  }
  function UndoComponent$render$lambda(this$UndoComponent) {
    return function ($receiver) {
      var tmp$;
      tmp$ = ButtonSize.Default;
      btsButton($receiver, ButtonLook.Default, tmp$, void 0, UndoComponent$render$lambda$lambda(this$UndoComponent), void 0, void 0, UndoComponent$render$lambda$lambda_0(this$UndoComponent));
      return Unit;
    };
  }
  UndoComponent.prototype.render_uj7sqx$ = function (element) {
    with_0(element, UndoComponent$render$lambda(this));
  };
  UndoComponent.prototype.undoable_u8di54$ = function (pastTenseDescription, undoPastTenseDescription, function_0) {
    if (equals(this.commandRecorder_0, NormalCommandRecorder_getInstance())) {
      var undoableGroup = new UndoableGroup(pastTenseDescription, undoPastTenseDescription);
      this.commandRecorder_0 = undoableGroup;
      try {
        var result = function_0();
        this.addUndoCommand_6abhnt$(undoableGroup);
        return result;
      }
      finally {
        this.commandRecorder_0 = NormalCommandRecorder_getInstance();
      }
    }
     else {
      return function_0();
    }
  };
  UndoComponent.prototype.notUndoable_klfg04$ = function (function_0) {
    var originalRecorder = this.commandRecorder_0;
    this.commandRecorder_0 = NoOpCommandRecorder_getInstance();
    try {
      return function_0();
    }
    finally {
      this.commandRecorder_0 = originalRecorder;
    }
  };
  UndoComponent.prototype.addUndoCommand_6abhnt$ = function (undoCommand) {
    clear(this.redoCommands_0);
    add(this.undoCommands_0, undoCommand);
  };
  function UndoComponent$watch$ObjectLiteral(closure$repository) {
    this.closure$repository = closure$repository;
  }
  function UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral(closure$isUpdate, closure$repository, closure$replacementWithID, closure$original, pastTenseDescription_0) {
    this.closure$isUpdate = closure$isUpdate;
    this.closure$repository = closure$repository;
    this.closure$replacementWithID = closure$replacementWithID;
    this.closure$original = closure$original;
    Command.call(this, pastTenseDescription_0);
  }
  function UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral(closure$repository, closure$original, closure$replacementWithID, closure$undoCommand, pastTenseDescription_0) {
    this.closure$repository = closure$repository;
    this.closure$original = closure$original;
    this.closure$replacementWithID = closure$replacementWithID;
    this.closure$undoCommand = closure$undoCommand;
    Command.call(this, pastTenseDescription_0);
  }
  UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral.prototype.executeAndGetOpposite = function () {
    this.closure$repository.save_tf6uce$(this.closure$original, this.closure$replacementWithID);
    return this.closure$undoCommand;
  };
  UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Command]
  };
  UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral.prototype.executeAndGetOpposite = function () {
    if (this.closure$isUpdate) {
      this.closure$repository.save_tf6uce$(this.closure$replacementWithID, ensureNotNull(this.closure$original));
    }
     else {
      this.closure$repository.remove_g5e3t3$(this.closure$replacementWithID);
    }
    var undoCommand = this;
    return new UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral(this.closure$repository, this.closure$original, this.closure$replacementWithID, undoCommand, this.closure$isUpdate ? 'Updated ' + toString(this.closure$original) : 'Added ' + this.closure$replacementWithID);
  };
  UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Command]
  };
  UndoComponent$watch$ObjectLiteral.prototype.onSaved_xwzc9q$ = function (original, replacementWithID) {
    var isUpdate = original != null && original.getID() != null;
    UndoComponent_getInstance().commandRecorder_0.addUndoCommandIfAppropriate_6abhnt$(new UndoComponent$watch$ObjectLiteral$onSaved$ObjectLiteral(isUpdate, this.closure$repository, replacementWithID, original, isUpdate ? 'Reverted ' + toString(original) : 'Deleted ' + replacementWithID));
  };
  function UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral(closure$repository, closure$item, pastTenseDescription_0) {
    this.closure$repository = closure$repository;
    this.closure$item = closure$item;
    Command.call(this, pastTenseDescription_0);
  }
  function UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral(closure$repository, closure$item, closure$undoCommand, pastTenseDescription_0) {
    this.closure$repository = closure$repository;
    this.closure$item = closure$item;
    this.closure$undoCommand = closure$undoCommand;
    Command.call(this, pastTenseDescription_0);
  }
  UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral.prototype.executeAndGetOpposite = function () {
    this.closure$repository.remove_g5e3t3$(this.closure$item);
    return this.closure$undoCommand;
  };
  UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Command]
  };
  UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral.prototype.executeAndGetOpposite = function () {
    this.closure$repository.save_tf6uce$(null, this.closure$item);
    var undoCommand = this;
    return new UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral$executeAndGetOpposite$ObjectLiteral(this.closure$repository, this.closure$item, undoCommand, 'Deleted ' + this.closure$item);
  };
  UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Command]
  };
  UndoComponent$watch$ObjectLiteral.prototype.onRemoved_11rb$ = function (item) {
    UndoComponent_getInstance().commandRecorder_0.addUndoCommandIfAppropriate_6abhnt$(new UndoComponent$watch$ObjectLiteral$onRemoved$ObjectLiteral(this.closure$repository, item, 'Restored ' + item));
  };
  UndoComponent$watch$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [RepositoryListener]
  };
  UndoComponent.prototype.watch_tko4xr$ = function (repository) {
    repository.addListener_56i51t$(new UndoComponent$watch$ObjectLiteral(repository));
  };
  UndoComponent.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'UndoComponent',
    interfaces: [UndoProvider]
  };
  var UndoComponent_instance = null;
  function UndoComponent_getInstance() {
    if (UndoComponent_instance === null) {
      new UndoComponent();
    }
    return UndoComponent_instance;
  }
  function undoComponent($receiver) {
    UndoComponent_getInstance().render_uj7sqx$($receiver);
  }
  function Command(pastTenseDescription_0) {
    this.pastTenseDescription = pastTenseDescription_0;
  }
  Command.prototype.toString = function () {
    return this.pastTenseDescription;
  };
  Command.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Command',
    interfaces: []
  };
  function CommandRecorder() {
  }
  CommandRecorder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CommandRecorder',
    interfaces: []
  };
  function NormalCommandRecorder() {
    NormalCommandRecorder_instance = this;
  }
  NormalCommandRecorder.prototype.addUndoCommandIfAppropriate_6abhnt$ = function (undoCommand) {
    UndoComponent_getInstance().addUndoCommand_6abhnt$(undoCommand);
  };
  NormalCommandRecorder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'NormalCommandRecorder',
    interfaces: [CommandRecorder]
  };
  var NormalCommandRecorder_instance = null;
  function NormalCommandRecorder_getInstance() {
    if (NormalCommandRecorder_instance === null) {
      new NormalCommandRecorder();
    }
    return NormalCommandRecorder_instance;
  }
  function NoOpCommandRecorder() {
    NoOpCommandRecorder_instance = this;
  }
  NoOpCommandRecorder.prototype.addUndoCommandIfAppropriate_6abhnt$ = function (undoCommand) {
  };
  NoOpCommandRecorder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'NoOpCommandRecorder',
    interfaces: [CommandRecorder]
  };
  var NoOpCommandRecorder_instance = null;
  function NoOpCommandRecorder_getInstance() {
    if (NoOpCommandRecorder_instance === null) {
      new NoOpCommandRecorder();
    }
    return NoOpCommandRecorder_instance;
  }
  function UndoableGroup(redoPastTenseDescription, undoPastTenseDescription) {
    Command.call(this, undoPastTenseDescription);
    this.redoPastTenseDescription_0 = redoPastTenseDescription;
    this.undoCommands_0 = ArrayList_init();
  }
  UndoableGroup.prototype.addUndoCommandIfAppropriate_6abhnt$ = function (undoCommand) {
    this.undoCommands_0.add_wxm5ur$(0, undoCommand);
  };
  function UndoableGroup$executeAndGetOpposite$ObjectLiteral(closure$redoCommands, closure$undoCommand, pastTenseDescription_0) {
    this.closure$redoCommands = closure$redoCommands;
    this.closure$undoCommand = closure$undoCommand;
    Command.call(this, pastTenseDescription_0);
  }
  UndoableGroup$executeAndGetOpposite$ObjectLiteral.prototype.executeAndGetOpposite = function () {
    var tmp$;
    tmp$ = this.closure$redoCommands.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      element.executeAndGetOpposite();
    }
    return this.closure$undoCommand;
  };
  UndoableGroup$executeAndGetOpposite$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Command]
  };
  UndoableGroup.prototype.executeAndGetOpposite = function () {
    var $receiver = this.undoCommands_0;
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(item.executeAndGetOpposite());
    }
    var redoCommands = reversed(destination);
    var undoCommand = this;
    return new UndoableGroup$executeAndGetOpposite$ObjectLiteral(redoCommands, undoCommand, this.redoPastTenseDescription_0);
  };
  UndoableGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UndoableGroup',
    interfaces: [CommandRecorder, Command]
  };
  function requestFileSystem($receiver, type, size, successCallback, errorCallback) {
    if (errorCallback === void 0)
      errorCallback = null;
    $receiver.requestFileSystem(type, size, successCallback, errorCallback);
  }
  function resolveLocalFileSystemURL($receiver, url, successCallback, errorCallback) {
    if (errorCallback === void 0)
      errorCallback = null;
    $receiver.resolveLocalFileSystemURL(url, successCallback, errorCallback);
  }
  function resolveLocalFileSystemURI($receiver, uri, successCallback, errorCallback) {
    if (errorCallback === void 0)
      errorCallback = null;
    $receiver.resolveLocalFileSystemURI(uri, successCallback, errorCallback);
  }
  function get_PERSISTENT($receiver) {
    return 0;
  }
  function get_TEMPORARY($receiver) {
    return 1;
  }
  function get_file($receiver) {
    return $receiver.file;
  }
  function get_cordova($receiver) {
    return $receiver.cordova;
  }
  function get_StatusBar($receiver) {
    return $receiver.StatusBar;
  }
  function initializeForCordova() {
    var tmp$, tmp$_0;
    var keyboard = (tmp$_0 = (tmp$ = get_cordova(window)) != null ? tmp$.plugins : null) != null ? tmp$_0.Keyboard : null;
    if (keyboard != null) {
      keyboard.hideKeyboardAccessoryBar(true);
      keyboard.disableScroll(true);
    }
    var statusBar = get_StatusBar(window);
    if (statusBar != null) {
      statusBar.styleDefault();
    }
  }
  function FirebaseRepositorySync(delegate, path, toData, firebaseApp) {
    this.delegate_3hwpkm$_0 = delegate;
    this.path = path;
    this.toData_cf2eeu$_0 = toData;
    this.firebaseApp = firebaseApp;
    this.unsyncedLocalStorageKey_fkdfyh$_0 = 'unsynced/firebase/' + this.path;
    this.originalValuesToSync_abkfty$_yr11sy$_0 = lazy(FirebaseRepositorySync$originalValuesToSync$lambda(this));
    this.valuesToSync_op1y89$_lur24k$_0 = lazy(FirebaseRepositorySync$valuesToSync$lambda(this));
    this.collectionRef_4yyr0w$_0 = this.firebaseApp.database().ref(this.path);
    this.on_jko9tn$_0(this.collectionRef_4yyr0w$_0, 'child_added', FirebaseRepositorySync_init$lambda(this));
    this.on_jko9tn$_0(this.collectionRef_4yyr0w$_0, 'child_changed', FirebaseRepositorySync_init$lambda_0(this));
    this.on_jko9tn$_0(this.collectionRef_4yyr0w$_0, 'child_removed', FirebaseRepositorySync_init$lambda_1(this));
    this.syncRemainingToFirebaseAsynchronously_qh0f4j$_0();
  }
  Object.defineProperty(FirebaseRepositorySync.prototype, 'originalValuesToSync_abkfty$_0', {
    get: function () {
      var $receiver = this.originalValuesToSync_abkfty$_yr11sy$_0;
      new PropertyMetadata('originalValuesToSync');
      return $receiver.value;
    }
  });
  Object.defineProperty(FirebaseRepositorySync.prototype, 'valuesToSync_op1y89$_0', {
    get: function () {
      var $receiver = this.valuesToSync_op1y89$_lur24k$_0;
      new PropertyMetadata('valuesToSync');
      return $receiver.value;
    }
  });
  function FirebaseRepositorySync$syncRemainingToFirebaseAsynchronously$lambda(this$FirebaseRepositorySync) {
    return function (it) {
      var firstOrNull_0 = firstOrNull(this$FirebaseRepositorySync.valuesToSync_op1y89$_0.entries);
      if (firstOrNull_0 != null) {
        this$FirebaseRepositorySync.syncToFirebase_djz06x$_0(firstOrNull_0);
        this$FirebaseRepositorySync.valuesToSync_op1y89$_0.remove_11rb$(firstOrNull_0.key);
        this$FirebaseRepositorySync.syncRemainingToFirebaseAsynchronously_qh0f4j$_0();
      }
      return Unit;
    };
  }
  FirebaseRepositorySync.prototype.syncRemainingToFirebaseAsynchronously_qh0f4j$_0 = function () {
    window.requestAnimationFrame(FirebaseRepositorySync$syncRemainingToFirebaseAsynchronously$lambda(this));
  };
  FirebaseRepositorySync.prototype.syncToFirebase_djz06x$_0 = function (valueToSync) {
    var id = new ID(valueToSync.key);
    var value = valueToSync.value;
    if (value != null)
      this.setInFirebase_cuy90t$_0(id, value);
    else
      this.removeInFirebase_h69whc$_0(id);
  };
  FirebaseRepositorySync.prototype.markAsNotSynced_ciggj1$_0 = function (id, value) {
    if (value != null) {
      this.valuesToSync_op1y89$_0.put_xwzc9p$(id._id, value);
    }
     else {
      this.valuesToSync_op1y89$_0.remove_11rb$(id._id);
    }
    this.storeValuesToSync_ugaevg$_0();
  };
  FirebaseRepositorySync.prototype.markAsSynced_2khr09$_0 = function (id) {
    this.valuesToSync_op1y89$_0.remove_11rb$(id._id);
    this.storeValuesToSync_ugaevg$_0();
  };
  FirebaseRepositorySync.prototype.storeValuesToSync_ugaevg$_0 = function () {
    var tmp$ = localStorage;
    var tmp$_0 = this.unsyncedLocalStorageKey_fkdfyh$_0;
    var $receiver = this.valuesToSync_op1y89$_0;
    var destination = ArrayList_init($receiver.size);
    var tmp$_1;
    tmp$_1 = $receiver.entries.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      destination.add_11rb$(to(item.key, item.value));
    }
    tmp$[tmp$_0] = JSON.stringify(json(copyToArray(destination).slice()));
  };
  FirebaseRepositorySync.prototype.on_jko9tn$_0 = function ($receiver, eventType, callback) {
    return $receiver.on(eventType, callback, null, null);
  };
  FirebaseRepositorySync.prototype.generateID = function () {
    return this.delegate_3hwpkm$_0.generateID();
  };
  FirebaseRepositorySync.prototype.valueWithId_uhyy02$_0 = function ($receiver) {
    return this.value_gf724h$_0($receiver).withID_83a66n$(ensureNotNull(this.get_id_5i2lna$_0($receiver)));
  };
  FirebaseRepositorySync.prototype.value_gf724h$_0 = function ($receiver) {
    var tmp$;
    return this.toData_cf2eeu$_0((tmp$ = $receiver.val()) == null || Kotlin.isType(tmp$, Any) ? tmp$ : throwCCE());
  };
  FirebaseRepositorySync.prototype.get_id_5i2lna$_0 = function ($receiver) {
    var tmp$, tmp$_0;
    return (tmp$_0 = (tmp$ = $receiver.key) != null ? toLong(tmp$) : null) != null ? ID_0(tmp$_0) : null;
  };
  FirebaseRepositorySync.prototype.list = function () {
    return this.delegate_3hwpkm$_0.list();
  };
  FirebaseRepositorySync.prototype.save_tf6uce$ = function (original, replacement) {
    var newID = this.delegate_3hwpkm$_0.save_tf6uce$(original, replacement);
    this.markAsNotSynced_ciggj1$_0(newID, replacement);
    this.setInFirebase_cuy90t$_0(newID, replacement);
    return newID;
  };
  function FirebaseRepositorySync$setInFirebase$lambda$lambda(closure$id, this$FirebaseRepositorySync) {
    return function (it) {
      this$FirebaseRepositorySync.markAsSynced_2khr09$_0(closure$id);
      return Unit;
    };
  }
  function FirebaseRepositorySync$setInFirebase$lambda$lambda_0(it) {
    handleError(it);
    return Unit;
  }
  function FirebaseRepositorySync$setInFirebase$lambda(this$FirebaseRepositorySync, closure$id, closure$replacement) {
    return function () {
      return this$FirebaseRepositorySync.collectionRef_4yyr0w$_0.child(closure$id.toString()).set(JSON.parse(JSON.stringify(closure$replacement.withID_83a66n$(closure$id)))).then(FirebaseRepositorySync$setInFirebase$lambda$lambda(closure$id, this$FirebaseRepositorySync), FirebaseRepositorySync$setInFirebase$lambda$lambda_0);
    };
  }
  FirebaseRepositorySync.prototype.setInFirebase_cuy90t$_0 = function (id, replacement) {
    handlingErrors('firebase set', FirebaseRepositorySync$setInFirebase$lambda(this, id, replacement));
  };
  FirebaseRepositorySync.prototype.remove_83a66n$ = function (id) {
    this.delegate_3hwpkm$_0.remove_83a66n$(id);
    this.markAsNotSynced_ciggj1$_0(id, null);
    this.removeInFirebase_h69whc$_0(id);
  };
  function FirebaseRepositorySync$removeInFirebase$lambda$lambda(closure$id, this$FirebaseRepositorySync) {
    return function (it) {
      this$FirebaseRepositorySync.markAsSynced_2khr09$_0(closure$id);
      return Unit;
    };
  }
  function FirebaseRepositorySync$removeInFirebase$lambda$lambda_0(it) {
    handleError(it);
    return Unit;
  }
  function FirebaseRepositorySync$removeInFirebase$lambda(this$FirebaseRepositorySync, closure$id) {
    return function () {
      return this$FirebaseRepositorySync.collectionRef_4yyr0w$_0.child(closure$id.toString()).remove().then(FirebaseRepositorySync$removeInFirebase$lambda$lambda(closure$id, this$FirebaseRepositorySync), FirebaseRepositorySync$removeInFirebase$lambda$lambda_0);
    };
  }
  FirebaseRepositorySync.prototype.removeInFirebase_h69whc$_0 = function (id) {
    handlingErrors('firebase remove', FirebaseRepositorySync$removeInFirebase$lambda(this, id));
  };
  FirebaseRepositorySync.prototype.addListener_56i51t$ = function (listener) {
    this.delegate_3hwpkm$_0.addListener_56i51t$(listener);
  };
  FirebaseRepositorySync.prototype.removeListener_56i51t$ = function (listener) {
    this.delegate_3hwpkm$_0.removeListener_56i51t$(listener);
  };
  Object.defineProperty(FirebaseRepositorySync.prototype, 'localStorageKeys', {
    get: function () {
      return this.delegate_3hwpkm$_0.localStorageKeys;
    }
  });
  function FirebaseRepositorySync$originalValuesToSync$lambda(this$FirebaseRepositorySync) {
    return function () {
      var tmp$, tmp$_0;
      var tmp$_1;
      if ((tmp$ = localStorage[this$FirebaseRepositorySync.unsyncedLocalStorageKey_fkdfyh$_0]) != null) {
        var this$FirebaseRepositorySync_0 = this$FirebaseRepositorySync;
        var block$result;
        block$break: do {
          try {
            var json = JSON.parse(tmp$);
            var $receiver = get_keys(json);
            var destination = ArrayList_init($receiver.length);
            var tmp$_2;
            for (tmp$_2 = 0; tmp$_2 !== $receiver.length; ++tmp$_2) {
              var item = $receiver[tmp$_2];
              var tmp$_3 = destination.add_11rb$;
              var tmp$_4;
              var tmp$_5;
              if ((tmp$_4 = json[item]) != null) {
                var tmp$_6;
                tmp$_5 = this$FirebaseRepositorySync_0.toData_cf2eeu$_0((tmp$_6 = tmp$_4) == null || Kotlin.isType(tmp$_6, Any) ? tmp$_6 : throwCCE());
              }
               else
                tmp$_5 = null;
              tmp$_3.call(destination, to(item, tmp$_5));
            }
            block$result = destination;
          }
           catch (t) {
            if (Kotlin.isType(t, Throwable)) {
              console.info(this$FirebaseRepositorySync_0.unsyncedLocalStorageKey_fkdfyh$_0 + ': ' + tmp$);
              console.error(t);
              block$result = null;
              break block$break;
            }
             else
              throw t;
          }
        }
         while (false);
        tmp$_1 = block$result;
      }
       else
        tmp$_1 = null;
      return (tmp$_0 = tmp$_1) != null ? mapOf(copyToArray(tmp$_0).slice()) : null;
    };
  }
  function FirebaseRepositorySync$valuesToSync$lambda(this$FirebaseRepositorySync) {
    return function () {
      var tmp$, tmp$_0;
      var tmp$_1;
      if ((tmp$_0 = (tmp$ = this$FirebaseRepositorySync.originalValuesToSync_abkfty$_0) != null ? toMutableMap(tmp$) : null) != null)
        tmp$_1 = tmp$_0;
      else {
        var $receiver = this$FirebaseRepositorySync.delegate_3hwpkm$_0.list();
        var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
        var tmp$_2;
        tmp$_2 = $receiver.iterator();
        while (tmp$_2.hasNext()) {
          var item = tmp$_2.next();
          var tmp$_3, tmp$_4;
          destination.add_11rb$((tmp$_4 = (tmp$_3 = item.getID()) != null ? tmp$_3._id : null) != null ? to(tmp$_4, item) : null);
        }
        tmp$_1 = mutableMapOf(copyToArray(filterNotNull(destination)).slice());
      }
      return tmp$_1;
    };
  }
  function FirebaseRepositorySync_init$lambda$lambda$lambda(closure$snapshot, this$FirebaseRepositorySync) {
    return function () {
      var id = ensureNotNull(this$FirebaseRepositorySync.get_id_5i2lna$_0(ensureNotNull(closure$snapshot)));
      var result = this$FirebaseRepositorySync.delegate_3hwpkm$_0.save_g5e3t3$(this$FirebaseRepositorySync.value_gf724h$_0(closure$snapshot).withID_83a66n$(id));
      this$FirebaseRepositorySync.markAsSynced_2khr09$_0(id);
      return result;
    };
  }
  function FirebaseRepositorySync_init$lambda$lambda(closure$snapshot, this$FirebaseRepositorySync) {
    return function () {
      return UndoComponent_getInstance().notUndoable_klfg04$(FirebaseRepositorySync_init$lambda$lambda$lambda(closure$snapshot, this$FirebaseRepositorySync));
    };
  }
  function FirebaseRepositorySync_init$lambda(this$FirebaseRepositorySync) {
    return function (snapshot, f) {
      handlingErrors('child_added', FirebaseRepositorySync_init$lambda$lambda(snapshot, this$FirebaseRepositorySync));
      return Unit;
    };
  }
  function FirebaseRepositorySync_init$lambda$lambda$lambda_0(closure$snapshot, this$FirebaseRepositorySync) {
    return function () {
      var id = ensureNotNull(this$FirebaseRepositorySync.get_id_5i2lna$_0(ensureNotNull(closure$snapshot)));
      var result = this$FirebaseRepositorySync.delegate_3hwpkm$_0.save_g5e3t3$(this$FirebaseRepositorySync.value_gf724h$_0(closure$snapshot).withID_83a66n$(id));
      this$FirebaseRepositorySync.markAsSynced_2khr09$_0(id);
      return result;
    };
  }
  function FirebaseRepositorySync_init$lambda$lambda_0(closure$snapshot, this$FirebaseRepositorySync) {
    return function () {
      return UndoComponent_getInstance().notUndoable_klfg04$(FirebaseRepositorySync_init$lambda$lambda$lambda_0(closure$snapshot, this$FirebaseRepositorySync));
    };
  }
  function FirebaseRepositorySync_init$lambda_0(this$FirebaseRepositorySync) {
    return function (snapshot, f) {
      handlingErrors('child_changed', FirebaseRepositorySync_init$lambda$lambda_0(snapshot, this$FirebaseRepositorySync));
      return Unit;
    };
  }
  function FirebaseRepositorySync_init$lambda$lambda$lambda_1(this$FirebaseRepositorySync, closure$id) {
    return function () {
      this$FirebaseRepositorySync.delegate_3hwpkm$_0.remove_83a66n$(closure$id);
      this$FirebaseRepositorySync.markAsSynced_2khr09$_0(closure$id);
      return Unit;
    };
  }
  function FirebaseRepositorySync_init$lambda$lambda_1(closure$snapshot, this$FirebaseRepositorySync) {
    return function () {
      var id = closure$snapshot != null ? this$FirebaseRepositorySync.get_id_5i2lna$_0(closure$snapshot) : null;
      if (id != null) {
        UndoComponent_getInstance().notUndoable_klfg04$(FirebaseRepositorySync_init$lambda$lambda$lambda_1(this$FirebaseRepositorySync, id));
      }
      return Unit;
    };
  }
  function FirebaseRepositorySync_init$lambda_1(this$FirebaseRepositorySync) {
    return function (snapshot, f) {
      handlingErrors('child_removed', FirebaseRepositorySync_init$lambda$lambda_1(snapshot, this$FirebaseRepositorySync));
      return Unit;
    };
  }
  FirebaseRepositorySync.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FirebaseRepositorySync',
    interfaces: [Repository]
  };
  function FirebaseAndLocalRepository(path, localPath, toData, firebaseApp) {
    return new FirebaseRepositorySync(new LocalStorageRepository(localPath, toData), path, toData, firebaseApp);
  }
  function ProtectionLevel(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function ProtectionLevel_initFields() {
    ProtectionLevel_initFields = function () {
    };
    ProtectionLevel$PRIVATE_instance = new ProtectionLevel('PRIVATE', 0);
    ProtectionLevel$PUBLIC_instance = new ProtectionLevel('PUBLIC', 1);
    ProtectionLevel$OWNED_instance = new ProtectionLevel('OWNED', 2);
  }
  var ProtectionLevel$PRIVATE_instance;
  function ProtectionLevel$PRIVATE_getInstance() {
    ProtectionLevel_initFields();
    return ProtectionLevel$PRIVATE_instance;
  }
  var ProtectionLevel$PUBLIC_instance;
  function ProtectionLevel$PUBLIC_getInstance() {
    ProtectionLevel_initFields();
    return ProtectionLevel$PUBLIC_instance;
  }
  var ProtectionLevel$OWNED_instance;
  function ProtectionLevel$OWNED_getInstance() {
    ProtectionLevel_initFields();
    return ProtectionLevel$OWNED_instance;
  }
  ProtectionLevel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ProtectionLevel',
    interfaces: [Enum]
  };
  function ProtectionLevel$values() {
    return [ProtectionLevel$PRIVATE_getInstance(), ProtectionLevel$PUBLIC_getInstance(), ProtectionLevel$OWNED_getInstance()];
  }
  ProtectionLevel.values = ProtectionLevel$values;
  function ProtectionLevel$valueOf(name) {
    switch (name) {
      case 'PRIVATE':
        return ProtectionLevel$PRIVATE_getInstance();
      case 'PUBLIC':
        return ProtectionLevel$PUBLIC_getInstance();
      case 'OWNED':
        return ProtectionLevel$OWNED_getInstance();
      default:throwISE('No enum constant client.ext.firebase.ProtectionLevel.' + name);
    }
  }
  ProtectionLevel.valueOf_61zpoe$ = ProtectionLevel$valueOf;
  function PublicWithChangeLogAndPrivateFirebaseRepository(relativePath, userId, toData, firebaseApp, categorizer) {
    var privateRepository = PrivateFirebaseRepository(userId, relativePath, toData, firebaseApp);
    var publicRepository = FirebaseAndLocalRepository('public/' + relativePath, relativePath, toData, firebaseApp);
    var publicRepositoryWithChangeLog = new RepositoryWithFirebaseChangeLog('publicChanges/' + relativePath, publicRepository);
    return new CompositeRepository(mapOf([to(ProtectionLevel$PUBLIC_getInstance(), publicRepositoryWithChangeLog), to(ProtectionLevel$PRIVATE_getInstance(), privateRepository)]), UndoComponent_getInstance(), categorizer);
  }
  function PrivateFirebaseRepository$lambda(closure$relativePath, closure$toData, closure$firebaseApp, closure$emptyRepository, closure$privateRepository) {
    return function (it) {
      var tmp$;
      var tmp$_0 = closure$privateRepository;
      var tmp$_1;
      if (it != null) {
        var closure$relativePath_0 = closure$relativePath;
        tmp$_1 = FirebaseAndLocalRepository('userPrivate/' + it + '/' + closure$relativePath_0, 'userPrivate/' + it + '/' + closure$relativePath_0, closure$toData, closure$firebaseApp);
      }
       else
        tmp$_1 = null;
      tmp$_0.delegate = (tmp$ = tmp$_1) != null ? tmp$ : closure$emptyRepository;
      return Unit;
    };
  }
  function PrivateFirebaseRepository(userId, relativePath, toData, firebaseApp) {
    var emptyRepository = new EmptyRepository();
    var privateRepository = new SwitchableRepository(emptyRepository, UndoComponent_getInstance());
    userId.onNext_qlkmfe$(PrivateFirebaseRepository$lambda(relativePath, toData, firebaseApp, emptyRepository, privateRepository));
    return privateRepository;
  }
  function get_keys($receiver) {
    return Object.keys($receiver);
  }
  function RepositoryWithFirebaseChangeLog(path, delegate) {
    this.delegate_m3ddg3$_0 = delegate;
    this.changeLogRef_fpj3lf$_0 = this.delegate_m3ddg3$_0.firebaseApp.database().ref(path);
  }
  RepositoryWithFirebaseChangeLog.prototype.generateID = function () {
    return this.delegate_m3ddg3$_0.generateID();
  };
  RepositoryWithFirebaseChangeLog.prototype.list = function () {
    return this.delegate_m3ddg3$_0.list();
  };
  function RepositoryWithFirebaseChangeLog$save$lambda(closure$replacement, this$RepositoryWithFirebaseChangeLog, closure$newID) {
    return function () {
      var changeLogEntry = new ChangeLogEntry(closure$replacement, 'me');
      return this$RepositoryWithFirebaseChangeLog.changeLogRef_fpj3lf$_0.child(closure$newID.toString()).push(changeLogEntry);
    };
  }
  RepositoryWithFirebaseChangeLog.prototype.save_tf6uce$ = function (original, replacement) {
    var newID = this.delegate_m3ddg3$_0.save_tf6uce$(original, replacement);
    handlingErrors('changelog: firebase push save', RepositoryWithFirebaseChangeLog$save$lambda(replacement, this, newID));
    return newID;
  };
  function RepositoryWithFirebaseChangeLog$remove$lambda(this$RepositoryWithFirebaseChangeLog, closure$id) {
    return function () {
      var changeLogEntry = new ChangeLogEntry(null, 'me');
      return this$RepositoryWithFirebaseChangeLog.changeLogRef_fpj3lf$_0.child(closure$id.toString()).push(changeLogEntry);
    };
  }
  RepositoryWithFirebaseChangeLog.prototype.remove_g5e3t3$ = function (item) {
    var id = item.getID();
    if (id != null) {
      this.delegate_m3ddg3$_0.remove_g5e3t3$(item);
      handlingErrors('changelog: firebase push removal', RepositoryWithFirebaseChangeLog$remove$lambda(this, id));
    }
  };
  RepositoryWithFirebaseChangeLog.prototype.addListener_56i51t$ = function (listener) {
    this.delegate_m3ddg3$_0.addListener_56i51t$(listener);
  };
  RepositoryWithFirebaseChangeLog.prototype.removeListener_56i51t$ = function (listener) {
    this.delegate_m3ddg3$_0.removeListener_56i51t$(listener);
  };
  Object.defineProperty(RepositoryWithFirebaseChangeLog.prototype, 'localStorageKeys', {
    get: function () {
      return this.delegate_m3ddg3$_0.localStorageKeys;
    }
  });
  RepositoryWithFirebaseChangeLog.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RepositoryWithFirebaseChangeLog',
    interfaces: [Repository]
  };
  function ChangeLogEntry(data, userId, changeDateMillis, id) {
    if (changeDateMillis === void 0)
      changeDateMillis = Moment.Companion.now().millisecondsSinceUnixEpoch.toInt();
    if (id === void 0)
      id = null;
    this.data = data;
    this.userId = userId;
    this.changeDateMillis = changeDateMillis;
    this.id = id;
  }
  ChangeLogEntry.prototype.getID = function () {
    return this.id;
  };
  ChangeLogEntry.prototype.withID_83a66n$ = function (id) {
    return this.copy_a7aoim$(void 0, void 0, void 0, id);
  };
  ChangeLogEntry.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ChangeLogEntry',
    interfaces: [WithID]
  };
  ChangeLogEntry.prototype.component1 = function () {
    return this.data;
  };
  ChangeLogEntry.prototype.component2 = function () {
    return this.userId;
  };
  ChangeLogEntry.prototype.component3 = function () {
    return this.changeDateMillis;
  };
  ChangeLogEntry.prototype.component4 = function () {
    return this.id;
  };
  ChangeLogEntry.prototype.copy_a7aoim$ = function (data, userId, changeDateMillis, id) {
    return new ChangeLogEntry(data === void 0 ? this.data : data, userId === void 0 ? this.userId : userId, changeDateMillis === void 0 ? this.changeDateMillis : changeDateMillis, id === void 0 ? this.id : id);
  };
  ChangeLogEntry.prototype.toString = function () {
    return 'ChangeLogEntry(data=' + Kotlin.toString(this.data) + (', userId=' + Kotlin.toString(this.userId)) + (', changeDateMillis=' + Kotlin.toString(this.changeDateMillis)) + (', id=' + Kotlin.toString(this.id)) + ')';
  };
  ChangeLogEntry.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.data) | 0;
    result = result * 31 + Kotlin.hashCode(this.userId) | 0;
    result = result * 31 + Kotlin.hashCode(this.changeDateMillis) | 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    return result;
  };
  ChangeLogEntry.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.data, other.data) && Kotlin.equals(this.userId, other.userId) && Kotlin.equals(this.changeDateMillis, other.changeDateMillis) && Kotlin.equals(this.id, other.id)))));
  };
  function toNormal($receiver) {
    var tmp$, tmp$_0;
    return new DataSet($receiver.label, $receiver.quickSummary, (tmp$ = $receiver.public) != null ? tmp$ : true, (tmp$_0 = $receiver.id) != null ? toNormal_6(tmp$_0) : null);
  }
  function toNormal_0($receiver) {
    var tmp$, tmp$_0;
    return new Tag($receiver.label, toNormalNotNull($receiver.dataSetId), $receiver.quickSummary, (tmp$ = $receiver.parentTagId) != null ? toNormal_6(tmp$) : null, (tmp$_0 = $receiver.id) != null ? toNormal_6(tmp$_0) : null);
  }
  function toNormal_1($receiver) {
    var tmp$;
    return new Option($receiver.label, toNormalNotNull($receiver.dataSetId), $receiver.quickSummary, $receiver.url, (tmp$ = $receiver.id) != null ? toNormal_6(tmp$) : null);
  }
  function toNormal_2($receiver) {
    var tmp$, tmp$_0;
    return new OptionTag(toNormalNotNull($receiver.optionId), toNormalNotNull($receiver.tagId), (tmp$ = $receiver.percentage) != null ? tmp$ : toByte(ensureNotNull($receiver.value) ? 100 : 0), toNormalNotNull($receiver.dataSetId), (tmp$_0 = $receiver.id) != null ? toNormal_6(tmp$_0) : null);
  }
  function toNormal_3($receiver) {
    var tmp$, tmp$_0;
    return new TagTag(toNormalNotNull($receiver.primaryTagId), toNormalNotNull($receiver.tagId), (tmp$ = $receiver.percentage) != null ? tmp$ : toByte(ensureNotNull($receiver.value) ? 100 : 0), toNormalNotNull($receiver.dataSetId), (tmp$_0 = $receiver.id) != null ? toNormal_6(tmp$_0) : null);
  }
  var appName;
  var appFilenamePrefix;
  var page;
  function setChildWithoutSplash(element, parentDiv) {
    addClass(page, ['hide-splash']);
    setChild(parentDiv, element, new Fade());
  }
  function UI() {
    UI_instance = this;
    this.dataSetId = toProperty(null);
    this.optionId = new Property(null);
    this.tagId = new Property(null);
    this.dataSetsScreen_5jbmfw$_0 = lazy(UI$dataSetsScreen$lambda);
    this.dataSetScreen_lrc1e5$_0 = lazy(UI$dataSetScreen$lambda(this));
    this.optionsModel = new OptionsModel(this.dataSetId);
    this.optionsScreen_bqvdhj$_0 = lazy(UI$optionsScreen$lambda(this));
    this.optionModel = OptionModel_init(this.optionId, this.dataSetId, this.optionsModel);
    this.optionDialog_cw0ckm$_0 = lazy(UI$optionDialog$lambda(this));
    this.optionEntryScreen_dzzjns$_0 = lazy(UI$optionEntryScreen$lambda(this));
    this.tagCriteriaModel = new TagCriteriaModel(this.optionsModel);
    this.tagCriteriaDialog = inContext('tagCriteriaDialog', UI$tagCriteriaDialog$lambda(this));
    this.optionTagModel = new OptionTagModel(this.dataSetId, this.tagCriteriaModel);
    this.optionTagDialog = inContext('optionTagDialog', UI$optionTagDialog$lambda(this));
    this.tagValueForOptionLabelModel_0 = new TagValueForOptionLabelModel(this.dataSetId, this.optionModel.label, this.optionModel.tagValues);
    this.tagValueForOptionLabelDialog_0 = inContext('tagValueForOptionLabelDialog', UI$tagValueForOptionLabelDialog$lambda(this));
    this.tagTagModel = new TagTagModel(this.dataSetId);
    this.tagTagDialog = inContext('tagTagDialog', UI$tagTagDialog$lambda(this));
    this.tagCriteriaDialog.showDialog();
    this.tagCriteriaDialog.hideDialog();
    this.optionTagDialog.showDialog();
    this.optionTagDialog.hideDialog();
    this.tagTagDialog.showDialog();
    this.tagTagDialog.hideDialog();
  }
  Object.defineProperty(UI.prototype, 'dataSetsScreen', {
    get: function () {
      var $receiver = this.dataSetsScreen_5jbmfw$_0;
      new PropertyMetadata('dataSetsScreen');
      return $receiver.value;
    }
  });
  Object.defineProperty(UI.prototype, 'dataSetScreen', {
    get: function () {
      var $receiver = this.dataSetScreen_lrc1e5$_0;
      new PropertyMetadata('dataSetScreen');
      return $receiver.value;
    }
  });
  Object.defineProperty(UI.prototype, 'optionsScreen', {
    get: function () {
      var $receiver = this.optionsScreen_bqvdhj$_0;
      new PropertyMetadata('optionsScreen');
      return $receiver.value;
    }
  });
  Object.defineProperty(UI.prototype, 'optionDialog', {
    get: function () {
      var $receiver = this.optionDialog_cw0ckm$_0;
      new PropertyMetadata('optionDialog');
      return $receiver.value;
    }
  });
  Object.defineProperty(UI.prototype, 'optionEntryScreen', {
    get: function () {
      var $receiver = this.optionEntryScreen_dzzjns$_0;
      new PropertyMetadata('optionEntryScreen');
      return $receiver.value;
    }
  });
  UI.prototype.showDialog_es32k7$ = function (optionTagIdPair) {
    this.optionTagModel.optionTagIdPair.set_11rb$(optionTagIdPair);
    this.optionTagDialog.showDialog();
  };
  UI.prototype.showDialog_42n4bc$ = function (tagTagIdPair) {
    this.tagTagModel.tagTagIdPair.set_11rb$(tagTagIdPair);
    this.tagTagDialog.showDialog();
  };
  UI.prototype.showTagValueForOptionLabelDialog_apv9qp$ = function (tagId) {
    this.tagValueForOptionLabelModel_0.tagIdProperty.set_11rb$(tagId);
    this.tagValueForOptionLabelDialog_0.showDialog();
  };
  UI.prototype.showTagCriteriaDialog_apv9qp$ = function (tagId) {
    this.tagCriteriaModel.criteriaTagId.set_11rb$(tagId);
    this.tagCriteriaDialog.showDialog();
  };
  function UI$dataSetsScreen$lambda$lambda() {
    return dataSetsScreen(new DataSetsModel());
  }
  function UI$dataSetsScreen$lambda() {
    return inContext('dataSetsScreen', UI$dataSetsScreen$lambda$lambda);
  }
  function UI$dataSetScreen$lambda$lambda(this$UI) {
    return function () {
      return dataSetScreen(new DataSetModel(this$UI.dataSetId));
    };
  }
  function UI$dataSetScreen$lambda(this$UI) {
    return function () {
      return inContext('dataSetScreen', UI$dataSetScreen$lambda$lambda(this$UI));
    };
  }
  function UI$optionsScreen$lambda$lambda(this$UI) {
    return function () {
      return optionsScreen(this$UI.optionsModel);
    };
  }
  function UI$optionsScreen$lambda(this$UI) {
    return function () {
      return inContext('optionsScreen', UI$optionsScreen$lambda$lambda(this$UI));
    };
  }
  function UI$optionDialog$lambda$lambda(this$UI) {
    return function () {
      return optionDialog(this$UI.optionModel);
    };
  }
  function UI$optionDialog$lambda(this$UI) {
    return function () {
      return inContext('optionDialog', UI$optionDialog$lambda$lambda(this$UI));
    };
  }
  function UI$optionEntryScreen$lambda$lambda(this$UI) {
    return function () {
      return optionEntryScreen(this$UI.optionModel);
    };
  }
  function UI$optionEntryScreen$lambda(this$UI) {
    return function () {
      return inContext('optionEntryScreen', UI$optionEntryScreen$lambda$lambda(this$UI));
    };
  }
  function UI$tagCriteriaDialog$lambda(this$UI) {
    return function () {
      return tagCriteriaDialog(this$UI.tagCriteriaModel);
    };
  }
  function UI$optionTagDialog$lambda(this$UI) {
    return function () {
      return optionTagDialog(this$UI.optionTagModel);
    };
  }
  function UI$tagValueForOptionLabelDialog$lambda(this$UI) {
    return function () {
      return tagValueForOptionLabelDialog(this$UI.tagValueForOptionLabelModel_0);
    };
  }
  function UI$tagTagDialog$lambda(this$UI) {
    return function () {
      return tagTagDialog(this$UI.tagTagModel);
    };
  }
  UI.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'UI',
    interfaces: []
  };
  var UI_instance = null;
  function UI_getInstance() {
    if (UI_instance === null) {
      new UI();
    }
    return UI_instance;
  }
  function main$lambda() {
    initializeForCordova();
    return Unit;
  }
  function main$lambda$lambda$lambda(closure$hash) {
    return function () {
      console.info("About to draw '" + closure$hash + "'");
      return Unit;
    };
  }
  function main$lambda$lambda(closure$divContainer, closure$previousHash) {
    return function (hash) {
      inContext("About to draw '" + hash + "'", main$lambda$lambda$lambda(hash));
      var firstHash = hash[0];
      if (equals(firstHash, '#dataSet')) {
        UI_getInstance().dataSetId.set_11rb$(hash.length > 1 ? new ID(hash[1]) : null);
        setChildWithoutSplash(UI_getInstance().dataSetScreen, closure$divContainer);
      }
       else if (equals(firstHash, '#option')) {
        UI_getInstance().optionId.set_11rb$(hash.length > 1 ? new ID(hash[1]) : null);
        setChildWithoutSplash(UI_getInstance().optionEntryScreen, closure$divContainer);
      }
       else if (equals(firstHash, '#options')) {
        UI_getInstance().dataSetId.set_11rb$(hash.length > 1 ? new ID(hash[1]) : null);
        setChildWithoutSplash(UI_getInstance().optionsScreen, closure$divContainer);
      }
       else {
        setChildWithoutSplash(UI_getInstance().dataSetsScreen, closure$divContainer);
      }
      if (!equals(firstHash, closure$previousHash.v)) {
        window.scrollTo(0.0, 0.0);
      }
      closure$previousHash.v = firstHash;
      return Unit;
    };
  }
  function main$lambda_0($receiver) {
    addClass($receiver, ['container-fluid']);
    var divContainer = div($receiver);
    var previousHash = {v: ''};
    get_splitHashProperty(window.location).onNext_qlkmfe$(main$lambda$lambda(divContainer, previousHash));
    return Unit;
  }
  function main(args) {
    try {
      inContext('initializeForCordova', main$lambda);
      PlatformProvider$Companion_getInstance().instance = JavascriptProvider_getInstance();
      with_0(page, main$lambda_0);
    }
     catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        handleError(e);
      }
       else
        throw e;
    }
  }
  var Collection = Kotlin.kotlin.collections.Collection;
  function Factory() {
    Factory_instance = this;
    this.firebaseConfig_0 = json([to('apiKey', 'AIzaSyDWMaEsZutYZFL4KSK39Z7AM9nCwWzpiqM'), to('authDomain', 'applicable-options.firebaseapp.com'), to('databaseURL', 'https://applicable-options.firebaseio.com'), to('projectId', 'applicable-options'), to('storageBucket', ''), to('messagingSenderId', '786137745184')]);
    this.firebaseApp_0 = initializeApp(this.firebaseConfig_0);
    this.userId = new Property(null);
    this.dataSetRepository = PublicWithChangeLogAndPrivateFirebaseRepository('dataSetList', this.userId, Factory$dataSetRepository$lambda, this.firebaseApp_0, Factory$dataSetRepository$lambda_0);
    this.optionRepository = PublicWithChangeLogAndPrivateFirebaseRepository('optionList', this.userId, Factory$optionRepository$lambda, this.firebaseApp_0, Factory$optionRepository$lambda_0(this));
    this.tagRepository = PublicWithChangeLogAndPrivateFirebaseRepository('tagList', this.userId, Factory$tagRepository$lambda, this.firebaseApp_0, Factory$tagRepository$lambda_0(this));
    this.optionTagRepository = PublicWithChangeLogAndPrivateFirebaseRepository('optionTagList', this.userId, Factory$optionTagRepository$lambda, this.firebaseApp_0, Factory$optionTagRepository$lambda_0(this));
    this.tagTagRepository = PublicWithChangeLogAndPrivateFirebaseRepository('tagTagList', this.userId, Factory$tagTagRepository$lambda, this.firebaseApp_0, Factory$tagTagRepository$lambda_0(this));
    this.allRepositories = listOf([this.dataSetRepository, this.optionRepository, this.optionTagRepository, this.tagRepository, this.tagTagRepository]);
    var $receiver = this.dataSetRepository.localStorageKeys;
    var all$result;
    all$break: do {
      var tmp$;
      if (Kotlin.isType($receiver, Collection) && $receiver.isEmpty()) {
        all$result = true;
        break all$break;
      }
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (!(localStorage[element] == null)) {
          all$result = false;
          break all$break;
        }
      }
      all$result = true;
    }
     while (false);
    if (all$result) {
      $.get('initial-data.json', Factory_init$lambda);
    }
    UndoComponent_getInstance().watch_tko4xr$(this.dataSetRepository);
    UndoComponent_getInstance().watch_tko4xr$(this.tagRepository);
    UndoComponent_getInstance().watch_tko4xr$(this.optionRepository);
    UndoComponent_getInstance().watch_tko4xr$(this.optionTagRepository);
    UndoComponent_getInstance().watch_tko4xr$(this.tagTagRepository);
    this.dataSetRepository.addListener_56i51t$(new Factory_init$ObjectLiteral());
    this.tagRepository.addListener_56i51t$(new Factory_init$ObjectLiteral_0());
    this.optionRepository.addListener_56i51t$(new Factory_init$ObjectLiteral_1());
  }
  Factory.prototype.add_wc5hdh$ = function (dataSet) {
    return this.dataSetRepository.save_g5e3t3$(dataSet);
  };
  Factory.prototype.add_jkj7jr$ = function (tag) {
    return this.tagRepository.save_g5e3t3$(tag);
  };
  Factory.prototype.add_2oncaw$ = function (option) {
    return this.optionRepository.save_g5e3t3$(option);
  };
  Factory.prototype.add_1kxy0y$ = function (optionTag) {
    return this.optionTagRepository.save_g5e3t3$(optionTag);
  };
  function Factory$dataSetRepository$lambda(it) {
    return toNormal(it);
  }
  function Factory$dataSetRepository$lambda_0(it) {
    return get_protectionLevel(it);
  }
  function Factory$optionRepository$lambda(it) {
    return toNormal_1(it);
  }
  function Factory$optionRepository$lambda_0(this$Factory) {
    return function (it) {
      var tmp$, tmp$_0;
      return (tmp$_0 = (tmp$ = this$Factory.dataSetRepository.find_83a66n$(it.dataSetId)) != null ? get_protectionLevel(tmp$) : null) != null ? tmp$_0 : ProtectionLevel$PUBLIC_getInstance();
    };
  }
  function Factory$tagRepository$lambda(it) {
    return toNormal_0(it);
  }
  function Factory$tagRepository$lambda_0(this$Factory) {
    return function (it) {
      var tmp$, tmp$_0;
      return (tmp$_0 = (tmp$ = this$Factory.dataSetRepository.find_83a66n$(it.dataSetId)) != null ? get_protectionLevel(tmp$) : null) != null ? tmp$_0 : ProtectionLevel$PUBLIC_getInstance();
    };
  }
  function Factory$optionTagRepository$lambda(it) {
    return toNormal_2(it);
  }
  function Factory$optionTagRepository$lambda_0(this$Factory) {
    return function (it) {
      var tmp$, tmp$_0;
      return (tmp$_0 = (tmp$ = this$Factory.dataSetRepository.find_83a66n$(it.dataSetId)) != null ? get_protectionLevel(tmp$) : null) != null ? tmp$_0 : ProtectionLevel$PUBLIC_getInstance();
    };
  }
  function Factory$tagTagRepository$lambda(it) {
    return toNormal_3(it);
  }
  function Factory$tagTagRepository$lambda_0(this$Factory) {
    return function (it) {
      var tmp$, tmp$_0;
      return (tmp$_0 = (tmp$ = this$Factory.dataSetRepository.find_83a66n$(it.dataSetId)) != null ? get_protectionLevel(tmp$) : null) != null ? tmp$_0 : ProtectionLevel$PUBLIC_getInstance();
    };
  }
  function Factory_init$lambda(initialData) {
    FileBackupComponent_getInstance().initializeData_za3rmp$(initialData);
    return Unit;
  }
  function Factory_init$ObjectLiteral() {
  }
  Factory_init$ObjectLiteral.prototype.onSaved_xwzc9q$ = function (original, replacementWithID) {
  };
  Factory_init$ObjectLiteral.prototype.onRemoved_11rb$ = function (item) {
    var dataSetId = item.id;
    if (dataSetId != null) {
      removeAll_0(Factory_getInstance().tagRepository, new TagByDataSetId(dataSetId));
      removeAll_0(Factory_getInstance().optionTagRepository, new OptionTagByDataSetId(dataSetId));
      removeAll_0(Factory_getInstance().optionRepository, new OptionByDataSetId(dataSetId));
    }
  };
  Factory_init$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [RepositoryListener]
  };
  function Factory_init$ObjectLiteral_0() {
  }
  Factory_init$ObjectLiteral_0.prototype.onSaved_xwzc9q$ = function (original, replacementWithID) {
  };
  Factory_init$ObjectLiteral_0.prototype.onRemoved_11rb$ = function (item) {
    var tagId = item.id;
    if (tagId != null) {
      removeAll_0(Factory_getInstance().optionTagRepository, new OptionTagByTagId(tagId));
      removeAll_0(Factory_getInstance().tagTagRepository, new TagTagByPrimaryTagId(tagId));
      removeAll_0(Factory_getInstance().tagTagRepository, new TagTagByTagId(tagId));
    }
  };
  Factory_init$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [RepositoryListener]
  };
  function Factory_init$ObjectLiteral_1() {
  }
  Factory_init$ObjectLiteral_1.prototype.onSaved_xwzc9q$ = function (original, replacementWithID) {
  };
  Factory_init$ObjectLiteral_1.prototype.onRemoved_11rb$ = function (item) {
    var optionId = item.id;
    if (optionId != null) {
      removeAll_0(Factory_getInstance().optionTagRepository, new OptionTagByOptionId(optionId));
    }
  };
  Factory_init$ObjectLiteral_1.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [RepositoryListener]
  };
  Factory.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Factory',
    interfaces: [ModelStorage]
  };
  var Factory_instance = null;
  function Factory_getInstance() {
    if (Factory_instance === null) {
      new Factory();
    }
    return Factory_instance;
  }
  function get_protectionLevel($receiver) {
    return $receiver.public ? ProtectionLevel$PUBLIC_getInstance() : ProtectionLevel$PRIVATE_getInstance();
  }
  function alphabeticalTagIds$lambda(this$alphabeticalTagIds) {
    return function (it) {
      return listProperty(this$alphabeticalTagIds, new TagByDataSetId(it));
    };
  }
  function alphabeticalTagIds$lambda_0(it) {
    return it.label;
  }
  var mapNotNullTo$lambda = wrapFunction(function () {
    return function (closure$transform, closure$destination) {
      return function (element) {
        var tmp$;
        if ((tmp$ = closure$transform(element)) != null) {
          closure$destination.add_11rb$(tmp$);
        }
        return Unit;
      };
    };
  });
  function alphabeticalTagIds$lambda_1(it) {
    var tmp$;
    var tmp$_0;
    if (it != null) {
      var destination = ArrayList_init();
      var tmp$_1;
      tmp$_1 = it.iterator();
      while (tmp$_1.hasNext()) {
        var element = tmp$_1.next();
        var tmp$_0_0;
        if ((tmp$_0_0 = element.id) != null) {
          destination.add_11rb$(tmp$_0_0);
        }
      }
      tmp$_0 = destination;
    }
     else
      tmp$_0 = null;
    return (tmp$ = tmp$_0) != null ? tmp$ : emptyList();
  }
  var compareBy$lambda_2 = wrapFunction(function () {
    var compareValues = Kotlin.kotlin.comparisons.compareValues_s00gnj$;
    return function (closure$selector) {
      return function (a, b) {
        var selector = closure$selector;
        return compareValues(selector(a), selector(b));
      };
    };
  });
  function Comparator$ObjectLiteral_2(closure$comparison) {
    this.closure$comparison = closure$comparison;
  }
  Comparator$ObjectLiteral_2.prototype.compare = function (a, b) {
    return this.closure$comparison(a, b);
  };
  Comparator$ObjectLiteral_2.$metadata$ = {kind: Kind_CLASS, interfaces: [Comparator]};
  function alphabeticalTagIds($receiver, dataSetId) {
    var tags = flatMapIfNotNull(dataSetId, alphabeticalTagIds$lambda($receiver));
    var alphabeticalSort = new Property(SortSpecification_init(new Comparator$ObjectLiteral_2(compareBy$lambda_2(alphabeticalTagIds$lambda_0))));
    return map(sortedWith(tags, alphabeticalSort), alphabeticalTagIds$lambda_1);
  }
  function findPercentageProperty$lambda(it) {
    return it != null ? it.percentage : null;
  }
  function findPercentageProperty($receiver, optionTagIdPair) {
    return map(findFirstOrNullProperty($receiver, new OptionTagByOptionTagIdPair(optionTagIdPair)), findPercentageProperty$lambda);
  }
  function findPercentageProperty$lambda_0(it) {
    return it != null ? it.percentage : null;
  }
  function findPercentageProperty_0($receiver, tagTagIdPair) {
    return map(findFirstOrNullProperty($receiver, new TagTagByTagTagIdPair(tagTagIdPair)), findPercentageProperty$lambda_0);
  }
  function JavascriptProvider() {
    JavascriptProvider_instance = this;
  }
  JavascriptProvider.prototype.now = function () {
    return new MomentDate(Moment.Companion.now());
  };
  JavascriptProvider.prototype.parseCurrency_61zpoe$ = function (input) {
    var result = parseFloat(replace(replace(replace(input, '$', ''), ')', ''), '(', '-'));
    if (isNaN(result)) {
      throw new IllegalArgumentException('invalid number: ' + input);
    }
    return result;
  };
  JavascriptProvider.prototype.formatCurrency_14dthe$ = function (input) {
    return numeral(input).format('0,0.00');
  };
  JavascriptProvider.prototype.formatCurrencyForInput_14dthe$ = function (input) {
    return numeral(input).format('0.00');
  };
  JavascriptProvider.prototype.toDate_s8cxhz$ = function (millis) {
    return new MomentDate(Moment.Companion.parseMillisecondsSinceUnixEpoch_s8cxhz$(millis));
  };
  JavascriptProvider.prototype.toDate_61zpoe$ = function (input) {
    return new MomentDate(Moment.Companion.parse_puj7f4$(input, 'YYYY-MM-DD'));
  };
  JavascriptProvider.prototype.toDate_qt1dr2$ = function (year, month, dayOfMonth) {
    var moment = Moment.Companion.parseMillisecondsSinceUnixEpoch_s8cxhz$(Kotlin.Long.ZERO);
    moment.year = year;
    moment.month = month;
    moment.dayOfMonth = dayOfMonth;
    return new MomentDate(moment);
  };
  JavascriptProvider.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'JavascriptProvider',
    interfaces: [PlatformProvider]
  };
  var JavascriptProvider_instance = null;
  function JavascriptProvider_getInstance() {
    if (JavascriptProvider_instance === null) {
      new JavascriptProvider();
    }
    return JavascriptProvider_instance;
  }
  function ToLocaleOptions(minimumFractionDigits, maximumFractionDigits, useGrouping) {
    if (minimumFractionDigits === void 0)
      minimumFractionDigits = 0;
    if (maximumFractionDigits === void 0)
      maximumFractionDigits = 20;
    if (useGrouping === void 0)
      useGrouping = true;
    this.minimumFractionDigits = minimumFractionDigits;
    this.maximumFractionDigits = maximumFractionDigits;
    this.useGrouping = useGrouping;
  }
  ToLocaleOptions.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ToLocaleOptions',
    interfaces: []
  };
  ToLocaleOptions.prototype.component1 = function () {
    return this.minimumFractionDigits;
  };
  ToLocaleOptions.prototype.component2 = function () {
    return this.maximumFractionDigits;
  };
  ToLocaleOptions.prototype.component3 = function () {
    return this.useGrouping;
  };
  ToLocaleOptions.prototype.copy_ydzd23$ = function (minimumFractionDigits, maximumFractionDigits, useGrouping) {
    return new ToLocaleOptions(minimumFractionDigits === void 0 ? this.minimumFractionDigits : minimumFractionDigits, maximumFractionDigits === void 0 ? this.maximumFractionDigits : maximumFractionDigits, useGrouping === void 0 ? this.useGrouping : useGrouping);
  };
  ToLocaleOptions.prototype.toString = function () {
    return 'ToLocaleOptions(minimumFractionDigits=' + Kotlin.toString(this.minimumFractionDigits) + (', maximumFractionDigits=' + Kotlin.toString(this.maximumFractionDigits)) + (', useGrouping=' + Kotlin.toString(this.useGrouping)) + ')';
  };
  ToLocaleOptions.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.minimumFractionDigits) | 0;
    result = result * 31 + Kotlin.hashCode(this.maximumFractionDigits) | 0;
    result = result * 31 + Kotlin.hashCode(this.useGrouping) | 0;
    return result;
  };
  ToLocaleOptions.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.minimumFractionDigits, other.minimumFractionDigits) && Kotlin.equals(this.maximumFractionDigits, other.maximumFractionDigits) && Kotlin.equals(this.useGrouping, other.useGrouping)))));
  };
  function LocalStorageRepository(localStorageKey, toData) {
    this.localStorageKey = localStorageKey;
    this.toData_pkh6ve$_0 = toData;
    this.listeners_ti1qnu$_0 = ArrayList_init(4);
    this.listForLocalStorage_ydv4ga$_au35g8$_0 = lazy(LocalStorageRepository$listForLocalStorage$lambda(this));
    this.localStorageKeys_z3p2ob$_0 = setOf_0(this.localStorageKey);
  }
  LocalStorageRepository.prototype.generateID = function () {
    return new ID((Math.random() * (new Kotlin.Long(-1, 2147483647)).toNumber()).toString());
  };
  Object.defineProperty(LocalStorageRepository.prototype, 'listForLocalStorage_ydv4ga$_0', {
    get: function () {
      var $receiver = this.listForLocalStorage_ydv4ga$_au35g8$_0;
      new PropertyMetadata('listForLocalStorage');
      return $receiver.value;
    }
  });
  LocalStorageRepository.prototype.list = function () {
    return toList(this.listForLocalStorage_ydv4ga$_0);
  };
  function LocalStorageRepository$save$lambda(this$LocalStorageRepository, closure$replacementWithID, closure$originalID, closure$originalWithID) {
    return function () {
      putIntoList(this$LocalStorageRepository.listForLocalStorage_ydv4ga$_0, closure$replacementWithID, closure$originalID);
      var $receiver = this$LocalStorageRepository.listeners_ti1qnu$_0;
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        element.onSaved_xwzc9q$(closure$originalWithID, closure$replacementWithID);
      }
      this$LocalStorageRepository.store_bwlu5g$_0();
      return Unit;
    };
  }
  LocalStorageRepository.prototype.save_tf6uce$ = function (original, replacement) {
    var tmp$;
    var originalID = (tmp$ = original != null ? original.getID() : null) != null ? tmp$ : replacement.getID();
    var replacementWithID = getOrGenerateID(this, originalID, replacement);
    var newID = ensureNotNull(replacementWithID.getID());
    var originalWithID = originalID != null ? original != null ? original.withID_83a66n$(originalID) : null : null;
    if (!equals(originalWithID, replacementWithID)) {
      console.info('Saving ' + replacement + ' over original=' + toString(original));
      UndoComponent_getInstance().undoable_u8di54$(originalID == null ? 'Added ' + replacementWithID : 'Updated ' + replacementWithID, originalID == null ? 'Deleted ' + replacementWithID : 'Reverted ' + toString(originalWithID), LocalStorageRepository$save$lambda(this, replacementWithID, originalID, originalWithID));
    }
    return newID;
  };
  LocalStorageRepository.prototype.replaceAll_tfr4my$ = function (entityJsonArray) {
    var parsedEntities = toMutableList(emptyList());
    var transform = this.toData_pkh6ve$_0;
    var tmp$;
    for (tmp$ = 0; tmp$ !== entityJsonArray.length; ++tmp$) {
      var item = entityJsonArray[tmp$];
      parsedEntities.add_11rb$(transform(item));
    }
    var priorEntities = toList(this.listForLocalStorage_ydv4ga$_0);
    this.listForLocalStorage_ydv4ga$_0.clear();
    this.listForLocalStorage_ydv4ga$_0.addAll_brywnq$(parsedEntities);
    var tmp$_0;
    tmp$_0 = this.listeners_ti1qnu$_0.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      var tmp$_1;
      tmp$_1 = priorEntities.iterator();
      while (tmp$_1.hasNext()) {
        var element_0 = tmp$_1.next();
        element.onRemoved_11rb$(element_0);
      }
    }
    var tmp$_2;
    tmp$_2 = this.listeners_ti1qnu$_0.iterator();
    while (tmp$_2.hasNext()) {
      var element_1 = tmp$_2.next();
      var tmp$_3;
      tmp$_3 = parsedEntities.iterator();
      while (tmp$_3.hasNext()) {
        var element_2 = tmp$_3.next();
        element_1.onSaved_xwzc9q$(null, element_2);
      }
    }
    this.store_bwlu5g$_0();
  };
  function LocalStorageRepository$remove$lambda(this$LocalStorageRepository, closure$index, closure$item) {
    return function () {
      this$LocalStorageRepository.listForLocalStorage_ydv4ga$_0.removeAt_za3lpa$(closure$index);
      var $receiver = this$LocalStorageRepository.listeners_ti1qnu$_0;
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        element.onRemoved_11rb$(closure$item);
      }
      this$LocalStorageRepository.store_bwlu5g$_0();
      return Unit;
    };
  }
  LocalStorageRepository.prototype.remove_83a66n$ = function (id) {
    var $receiver = this.listForLocalStorage_ydv4ga$_0;
    var indexOfFirst$result;
    indexOfFirst$break: do {
      var tmp$;
      var index = 0;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        if (equals(item.getID(), id)) {
          indexOfFirst$result = index;
          break indexOfFirst$break;
        }
        index = index + 1 | 0;
      }
      indexOfFirst$result = -1;
    }
     while (false);
    var index_0 = indexOfFirst$result;
    if (index_0 >= 0) {
      var item_0 = this.listForLocalStorage_ydv4ga$_0.get_za3lpa$(index_0);
      console.info('Removing ' + item_0);
      UndoComponent_getInstance().undoable_u8di54$('Deleted ' + item_0, 'Restored ' + item_0, LocalStorageRepository$remove$lambda(this, index_0, item_0));
    }
  };
  LocalStorageRepository.prototype.addListener_56i51t$ = function (listener) {
    this.listeners_ti1qnu$_0.add_11rb$(listener);
  };
  LocalStorageRepository.prototype.removeListener_56i51t$ = function (listener) {
    this.listeners_ti1qnu$_0.remove_11rb$(listener);
  };
  LocalStorageRepository.prototype.store_bwlu5g$_0 = function () {
    localStorage[this.localStorageKey] = JSON.stringify(this.listForLocalStorage_ydv4ga$_0);
  };
  Object.defineProperty(LocalStorageRepository.prototype, 'localStorageKeys', {
    get: function () {
      return this.localStorageKeys_z3p2ob$_0;
    }
  });
  function LocalStorageRepository$listForLocalStorage$lambda(this$LocalStorageRepository) {
    return function () {
      var tmp$, tmp$_0;
      var tmp$_1;
      if ((tmp$ = localStorage[this$LocalStorageRepository.localStorageKey]) != null) {
        var this$LocalStorageRepository_0 = this$LocalStorageRepository;
        var block$result;
        block$break: do {
          try {
            var jsArray = JSON.parse(tmp$);
            var destination = ArrayList_init(jsArray.length);
            var tmp$_2;
            for (tmp$_2 = 0; tmp$_2 !== jsArray.length; ++tmp$_2) {
              var item = jsArray[tmp$_2];
              destination.add_11rb$(this$LocalStorageRepository_0.toData_pkh6ve$_0(item));
            }
            block$result = destination;
          }
           catch (t) {
            if (Kotlin.isType(t, Throwable)) {
              console.info(this$LocalStorageRepository_0.localStorageKey + ': ' + tmp$);
              console.error(t);
              block$result = null;
              break block$break;
            }
             else
              throw t;
          }
        }
         while (false);
        tmp$_1 = block$result;
      }
       else
        tmp$_1 = null;
      var listOrNull = tmp$_1;
      return (tmp$_0 = listOrNull != null ? ArrayList_init_0(listOrNull) : null) != null ? tmp$_0 : ArrayList_init();
    };
  }
  LocalStorageRepository.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LocalStorageRepository',
    interfaces: [Repository]
  };
  function MomentDate(moment) {
    this.moment = moment;
  }
  Object.defineProperty(MomentDate.prototype, 'year', {
    get: function () {
      return this.moment.year;
    }
  });
  Object.defineProperty(MomentDate.prototype, 'month', {
    get: function () {
      return this.moment.month;
    }
  });
  Object.defineProperty(MomentDate.prototype, 'dayOfMonth', {
    get: function () {
      return this.moment.dayOfMonth;
    }
  });
  Object.defineProperty(MomentDate.prototype, 'millisecondsSinceUnixEpoch', {
    get: function () {
      return this.moment.millisecondsSinceUnixEpoch;
    }
  });
  MomentDate.prototype.toString = function () {
    return this.moment.format_61zpoe$('ll');
  };
  MomentDate.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MomentDate',
    interfaces: [ProviderDate]
  };
  function toRichDate($receiver) {
    return toRichDate_0(new MomentDate($receiver));
  }
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  function RepositoryCache(repository) {
    this.repository = repository;
    this.cache_0 = LinkedHashMap_init();
    this.listCache_0 = LinkedHashMap_init();
    this.repository.addListener_56i51t$(new RepositoryCache_init$ObjectLiteral(this));
  }
  RepositoryCache.prototype.find_83a66n$ = function (id) {
    var $receiver = this.cache_0;
    var tmp$;
    var value = $receiver.get_11rb$(id);
    if (value == null) {
      var answer = toProperty(this.repository.find_83a66n$(id));
      $receiver.put_xwzc9p$(id, answer);
      tmp$ = answer;
    }
     else {
      tmp$ = value;
    }
    return tmp$;
  };
  RepositoryCache.prototype.listProperty_b8fuo5$ = function (query) {
    var tmp$;
    var $receiver = this.listCache_0;
    var tmp$_0;
    var value = $receiver.get_11rb$(query);
    if (value == null) {
      var $receiver_0 = this.repository.list_fmyu0x$(query.criteria);
      var destination = ArrayList_init(collectionSizeOrDefault($receiver_0, 10));
      var tmp$_1;
      tmp$_1 = $receiver_0.iterator();
      while (tmp$_1.hasNext()) {
        var item = tmp$_1.next();
        destination.add_11rb$(query.selector.invoke_g5e3t3$(item));
      }
      var answer = new Property(distinct(destination));
      $receiver.put_xwzc9p$(query, answer);
      tmp$_0 = answer;
    }
     else {
      tmp$_0 = value;
    }
    return Kotlin.isType(tmp$ = tmp$_0, ReadOnlyProperty) ? tmp$ : throwCCE();
  };
  function RepositoryCache_init$ObjectLiteral(this$RepositoryCache) {
    this.this$RepositoryCache = this$RepositoryCache;
  }
  function RepositoryCache_init$ObjectLiteral$onSaved$lambda$lambda(closure$original, closure$query, closure$replacementWithID) {
    return function (list) {
      var tmp$;
      if (closure$original != null && closure$query.criteria.invoke_g5e3t3$(closure$original)) {
        tmp$ = list.indexOf_11rb$(closure$query.selector.invoke_g5e3t3$(closure$original));
      }
       else {
        tmp$ = -1;
      }
      var index = tmp$;
      if (closure$query.criteria.invoke_g5e3t3$(closure$replacementWithID)) {
        var newFieldValue = closure$query.selector.invoke_g5e3t3$(closure$replacementWithID);
        if (index >= 0) {
          list.set_wxm5ur$(index, newFieldValue);
        }
         else {
          list.add_11rb$(newFieldValue);
        }
      }
       else if (index >= 0) {
        list.removeAt_za3lpa$(index);
      }
      return Unit;
    };
  }
  RepositoryCache_init$ObjectLiteral.prototype.onSaved_xwzc9q$ = function (original, replacementWithID) {
    var tmp$;
    var id = ensureNotNull(replacementWithID.getID());
    (tmp$ = this.this$RepositoryCache.cache_0.get_11rb$(id)) != null ? (tmp$.set_11rb$(replacementWithID), Unit) : null;
    var tmp$_0;
    tmp$_0 = this.this$RepositoryCache.listCache_0.entries.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      var query = element.key;
      var listProperty = element.value;
      modifyList(listProperty, RepositoryCache_init$ObjectLiteral$onSaved$lambda$lambda(original, query, replacementWithID));
    }
  };
  function RepositoryCache_init$ObjectLiteral$onRemoved$lambda$lambda(closure$query, closure$item) {
    return function (it) {
      it.remove_11rb$(closure$query.selector.invoke_g5e3t3$(closure$item));
      return Unit;
    };
  }
  function RepositoryCache_init$ObjectLiteral$onRemoved$lambda$lambda_0(closure$query, closure$item) {
    return function (it) {
      it.remove_11rb$(closure$query.selector.invoke_g5e3t3$(closure$item));
      return Unit;
    };
  }
  RepositoryCache_init$ObjectLiteral.prototype.onRemoved_11rb$ = function (item) {
    var tmp$;
    if ((tmp$ = item.getID()) != null) {
      this.this$RepositoryCache;
      var tmp$_0;
      (tmp$_0 = this.this$RepositoryCache.cache_0.get_11rb$(tmp$)) != null && (tmp$_0.set_11rb$(null), Unit);
    }
    var tmp$_1;
    tmp$_1 = this.this$RepositoryCache.listCache_0.entries.iterator();
    while (tmp$_1.hasNext()) {
      var element = tmp$_1.next();
      var query = element.key;
      var listProperty = element.value;
      if (query.criteria.invoke_g5e3t3$(item)) {
        modifyList(listProperty, RepositoryCache_init$ObjectLiteral$onRemoved$lambda$lambda(query, item));
      }
    }
    var tmp$_2;
    tmp$_2 = this.this$RepositoryCache.listCache_0.entries.iterator();
    while (tmp$_2.hasNext()) {
      var element_0 = tmp$_2.next();
      var query_0 = element_0.key;
      var listProperty_0 = element_0.value;
      if (query_0.criteria.invoke_g5e3t3$(item)) {
        modifyList(listProperty_0, RepositoryCache_init$ObjectLiteral$onRemoved$lambda$lambda_0(query_0, item));
      }
    }
  };
  RepositoryCache_init$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [RepositoryListener]
  };
  RepositoryCache.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RepositoryCache',
    interfaces: []
  };
  var repositoryCaches;
  function repositoryCache(repository) {
    var tmp$;
    var $receiver = repositoryCaches;
    var tmp$_0;
    var value = $receiver.get_11rb$(repository);
    if (value == null) {
      var answer = new RepositoryCache(repository);
      $receiver.put_xwzc9p$(repository, answer);
      tmp$_0 = answer;
    }
     else {
      tmp$_0 = value;
    }
    return Kotlin.isType(tmp$ = tmp$_0, RepositoryCache) ? tmp$ : throwCCE();
  }
  function findProperty($receiver, id) {
    return repositoryCache($receiver).find_83a66n$(id);
  }
  function idListProperty($receiver, criteria) {
    if (criteria === void 0)
      criteria = allItems();
    return listProperty_0($receiver, new IdFieldSelector(), criteria);
  }
  function RepositoryQuery(selector, criteria) {
    this.selector = selector;
    this.criteria = criteria;
  }
  RepositoryQuery.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RepositoryQuery',
    interfaces: []
  };
  RepositoryQuery.prototype.component1 = function () {
    return this.selector;
  };
  RepositoryQuery.prototype.component2 = function () {
    return this.criteria;
  };
  RepositoryQuery.prototype.copy_gr2944$ = function (selector, criteria) {
    return new RepositoryQuery(selector === void 0 ? this.selector : selector, criteria === void 0 ? this.criteria : criteria);
  };
  RepositoryQuery.prototype.toString = function () {
    return 'RepositoryQuery(selector=' + Kotlin.toString(this.selector) + (', criteria=' + Kotlin.toString(this.criteria)) + ')';
  };
  RepositoryQuery.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.selector) | 0;
    result = result * 31 + Kotlin.hashCode(this.criteria) | 0;
    return result;
  };
  RepositoryQuery.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.selector, other.selector) && Kotlin.equals(this.criteria, other.criteria)))));
  };
  function list($receiver, selector, criteria) {
    if (criteria === void 0)
      criteria = allItems();
    return listProperty_0($receiver, selector, criteria).get();
  }
  function listProperty($receiver, criteria) {
    if (criteria === void 0)
      criteria = allItems();
    return repositoryCache($receiver).listProperty_b8fuo5$(new RepositoryQuery(new SelfSelector(), criteria));
  }
  function listProperty_0($receiver, selector, criteria) {
    if (criteria === void 0)
      criteria = allItems();
    return repositoryCache($receiver).listProperty_b8fuo5$(new RepositoryQuery(selector, criteria));
  }
  function findFirstOrNullProperty$lambda(this$findFirstOrNullProperty) {
    return function (it) {
      var tmp$;
      var tmp$_0;
      tmp$_0 = (tmp$ = firstOrNull_0(it)) != null ? findProperty(this$findFirstOrNullProperty, tmp$) : null;
      return tmp$_0;
    };
  }
  function findFirstOrNullProperty($receiver, criteria) {
    if (criteria === void 0)
      criteria = allItems();
    return flatMapOrNull(idListProperty($receiver, criteria), findFirstOrNullProperty$lambda($receiver));
  }
  function handleError(throwable) {
    window.alert(currentContext + ': ' + throwable.toString());
  }
  function handlingErrors$lambda(closure$f) {
    return function () {
      try {
        closure$f();
      }
       catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          handleError(e);
        }
         else
          throw e;
      }
      return Unit;
    };
  }
  function handlingErrors(contextName, f) {
    return inContext(contextName, handlingErrors$lambda(f));
  }
  var HIGH_AMOUNT;
  function toNormal_4($receiver) {
    if ($receiver == null) {
      return null;
    }
     else if ($receiver.high_ === undefined) {
      var number = $receiver;
      return numberToLong(number);
    }
     else {
      return Kotlin.Long.fromInt($receiver.high_).multiply(new Kotlin.Long(0, 1)).add($receiver.low_ >= 0 ? Kotlin.Long.fromInt($receiver.low_) : Kotlin.Long.fromInt($receiver.low_).add(new Kotlin.Long(0, 1)));
    }
  }
  function toID($receiver) {
    return new ID($receiver);
  }
  function toMoment($receiver) {
    var tmp$;
    return (Kotlin.isType(tmp$ = $receiver.date, MomentDate) ? tmp$ : throwCCE()).moment;
  }
  function toNormal_5($receiver) {
    return new RichDate_3($receiver.months, $receiver.days);
  }
  function toNormal_6($receiver) {
    var tmp$, tmp$_0, tmp$_1;
    return (tmp$_1 = (tmp$_0 = (tmp$ = toNormal_4($receiver.id)) != null ? tmp$.toString() : null) != null ? tmp$_0 : $receiver._id) != null ? new ID(tmp$_1) : null;
  }
  function toNormalNotNull($receiver) {
    var normal = $receiver != null ? toNormal_6($receiver) : null;
    if (normal == null) {
      console.error('IDJS is null!');
      return new ID('500');
    }
     else {
      return normal;
    }
  }
  function orElse$lambda(closure$alternate) {
    return function (it) {
      return it != null ? toProperty(it) : closure$alternate;
    };
  }
  function orElse($receiver, alternate) {
    return flatMap($receiver, orElse$lambda(alternate));
  }
  function mapEachReusing$lambda(closure$transform) {
    return function (oldResult, list) {
      var tmp$;
      var tmp$_0;
      if (oldResult != null) {
        var destination = LinkedHashMap_init();
        var tmp$_1;
        tmp$_1 = oldResult.iterator();
        while (tmp$_1.hasNext()) {
          var element = tmp$_1.next();
          var key = element;
          var tmp$_0_0;
          var value = destination.get_11rb$(key);
          if (value == null) {
            var answer = ArrayList_init();
            destination.put_xwzc9p$(key, answer);
            tmp$_0_0 = answer;
          }
           else {
            tmp$_0_0 = value;
          }
          var list_0 = tmp$_0_0;
          list_0.add_11rb$(element);
        }
        tmp$_0 = destination;
      }
       else
        tmp$_0 = null;
      var oldResultMap = tmp$_0;
      var tmp$_2;
      if (list != null) {
        var transform = closure$transform;
        var destination_0 = ArrayList_init(collectionSizeOrDefault(list, 10));
        var tmp$_3;
        tmp$_3 = list.iterator();
        while (tmp$_3.hasNext()) {
          var item = tmp$_3.next();
          destination_0.add_11rb$(transform(item));
        }
        tmp$_2 = destination_0;
      }
       else
        tmp$_2 = null;
      var tmp$_4;
      if ((tmp$ = tmp$_2) != null) {
        var destination_1 = ArrayList_init(collectionSizeOrDefault(tmp$, 10));
        var tmp$_5;
        tmp$_5 = tmp$.iterator();
        while (tmp$_5.hasNext()) {
          var item_0 = tmp$_5.next();
          var tmp$_6, tmp$_7;
          destination_1.add_11rb$((tmp$_7 = (tmp$_6 = oldResultMap != null ? oldResultMap.get_11rb$(item_0) : null) != null ? first(tmp$_6) : null) != null ? tmp$_7 : item_0);
        }
        tmp$_4 = destination_1;
      }
       else
        tmp$_4 = null;
      return tmp$_4;
    };
  }
  function mapEachReusing($receiver, transform) {
    return collectAsDefault($receiver, mapEachReusing$lambda(transform));
  }
  function mapEachReusingByID$lambda(closure$mapByID, closure$transform) {
    return function (it) {
      var tmp$;
      if (it != null) {
        var destination = ArrayList_init(collectionSizeOrDefault(it, 10));
        var tmp$_0;
        tmp$_0 = it.iterator();
        while (tmp$_0.hasNext()) {
          var item = tmp$_0.next();
          destination.add_11rb$(mapWithCacheByID(closure$mapByID, item, closure$transform));
        }
        tmp$ = destination;
      }
       else
        tmp$ = null;
      return tmp$;
    };
  }
  function mapEachReusingByID($receiver, transform) {
    var mapByID = LinkedHashMap_init();
    return mapAsDefault($receiver, mapEachReusingByID$lambda(mapByID, transform));
  }
  function mapWithCacheByID($receiver, entity, transform) {
    var tmp$, tmp$_0;
    var tmp$_1;
    if ((tmp$ = entity.getID()) != null) {
      var tmp$_2;
      var value = $receiver.get_11rb$(tmp$);
      if (value == null) {
        var answer = transform(entity);
        $receiver.put_xwzc9p$(tmp$, answer);
        tmp$_2 = answer;
      }
       else {
        tmp$_2 = value;
      }
      tmp$_1 = tmp$_2;
    }
     else
      tmp$_1 = null;
    return (tmp$_0 = tmp$_1) != null ? tmp$_0 : transform(entity);
  }
  function toggleItem$lambda(closure$item) {
    return function (it) {
      if (it.contains_11rb$(closure$item)) {
        return minus_0(it, closure$item);
      }
       else {
        return plus_1(it, closure$item);
      }
    };
  }
  function toggleItem($receiver, item) {
    modify($receiver, toggleItem$lambda(item));
  }
  function contains$lambda(closure$item) {
    return function (it) {
      return contains(it, closure$item);
    };
  }
  function contains_1($receiver, item) {
    return map($receiver, contains$lambda(item));
  }
  function contains$lambda_0(collection, item) {
    return contains(collection, item);
  }
  function contains_2($receiver, itemProperty) {
    return mapWith($receiver, itemProperty, contains$lambda_0);
  }
  function emptyToNull($receiver) {
    var tmp$ = $receiver == null;
    if (!tmp$) {
      tmp$ = $receiver.length === 0;
    }
    return tmp$ ? null : $receiver;
  }
  function Hello() {
    Hello_instance = this;
  }
  Hello.prototype.hello_61zpoe$ = function (name) {
    if (name === void 0)
      name = 'World';
    return 'Hello ' + name;
  };
  Hello.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Hello',
    interfaces: []
  };
  var Hello_instance = null;
  function Hello_getInstance() {
    if (Hello_instance === null) {
      new Hello();
    }
    return Hello_instance;
  }
  function DataSet(label, quickSummary, public_0, id) {
    if (quickSummary === void 0)
      quickSummary = null;
    if (public_0 === void 0)
      public_0 = true;
    if (id === void 0)
      id = null;
    this.label = label;
    this.quickSummary = quickSummary;
    this.public = public_0;
    this.id = id;
  }
  DataSet.prototype.getID = function () {
    return this.id;
  };
  DataSet.prototype.withID_83a66n$ = function (id) {
    return this.copy_qy47k6$(void 0, void 0, void 0, id);
  };
  DataSet.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DataSet',
    interfaces: [WithID]
  };
  DataSet.prototype.component1 = function () {
    return this.label;
  };
  DataSet.prototype.component2 = function () {
    return this.quickSummary;
  };
  DataSet.prototype.component3 = function () {
    return this.public;
  };
  DataSet.prototype.component4 = function () {
    return this.id;
  };
  DataSet.prototype.copy_qy47k6$ = function (label, quickSummary, public_0, id) {
    return new DataSet(label === void 0 ? this.label : label, quickSummary === void 0 ? this.quickSummary : quickSummary, public_0 === void 0 ? this.public : public_0, id === void 0 ? this.id : id);
  };
  DataSet.prototype.toString = function () {
    return 'DataSet(label=' + Kotlin.toString(this.label) + (', quickSummary=' + Kotlin.toString(this.quickSummary)) + (', public=' + Kotlin.toString(this.public)) + (', id=' + Kotlin.toString(this.id)) + ')';
  };
  DataSet.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.label) | 0;
    result = result * 31 + Kotlin.hashCode(this.quickSummary) | 0;
    result = result * 31 + Kotlin.hashCode(this.public) | 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    return result;
  };
  DataSet.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.label, other.label) && Kotlin.equals(this.quickSummary, other.quickSummary) && Kotlin.equals(this.public, other.public) && Kotlin.equals(this.id, other.id)))));
  };
  function Tag(label, dataSetId, quickSummary, parentTagId, id) {
    if (quickSummary === void 0)
      quickSummary = null;
    if (parentTagId === void 0)
      parentTagId = null;
    if (id === void 0)
      id = null;
    this.label = label;
    this.dataSetId = dataSetId;
    this.quickSummary = quickSummary;
    this.parentTagId = parentTagId;
    this.id = id;
  }
  Tag.prototype.getID = function () {
    return this.id;
  };
  Tag.prototype.withID_83a66n$ = function (id) {
    return this.copy_kdjmoa$(void 0, void 0, void 0, void 0, id);
  };
  Tag.prototype.containsAllLowerCaseSubstrings_mhpeer$ = function (lowerCaseSubstrings) {
    var all$result;
    all$break: do {
      var tmp$;
      if (Kotlin.isType(lowerCaseSubstrings, Collection) && lowerCaseSubstrings.isEmpty()) {
        all$result = true;
        break all$break;
      }
      tmp$ = lowerCaseSubstrings.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var tmp$_0 = contains_0(this.label.toLowerCase(), element);
        if (!tmp$_0) {
          var tmp$_1 = this.quickSummary != null;
          if (tmp$_1) {
            tmp$_1 = contains_0(this.quickSummary.toLowerCase(), element);
          }
          tmp$_0 = tmp$_1;
        }
        if (!tmp$_0) {
          all$result = false;
          break all$break;
        }
      }
      all$result = true;
    }
     while (false);
    return all$result;
  };
  Tag.prototype.getDisplayLabel_mz3mdy$ = function (percentage) {
    return percentage != null && percentage > 0 && percentage < 100 ? toString(percentage) + '% ' + this.label : this.label;
  };
  Tag.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Tag',
    interfaces: [WithID]
  };
  Tag.prototype.component1 = function () {
    return this.label;
  };
  Tag.prototype.component2 = function () {
    return this.dataSetId;
  };
  Tag.prototype.component3 = function () {
    return this.quickSummary;
  };
  Tag.prototype.component4 = function () {
    return this.parentTagId;
  };
  Tag.prototype.component5 = function () {
    return this.id;
  };
  Tag.prototype.copy_kdjmoa$ = function (label, dataSetId, quickSummary, parentTagId, id) {
    return new Tag(label === void 0 ? this.label : label, dataSetId === void 0 ? this.dataSetId : dataSetId, quickSummary === void 0 ? this.quickSummary : quickSummary, parentTagId === void 0 ? this.parentTagId : parentTagId, id === void 0 ? this.id : id);
  };
  Tag.prototype.toString = function () {
    return 'Tag(label=' + Kotlin.toString(this.label) + (', dataSetId=' + Kotlin.toString(this.dataSetId)) + (', quickSummary=' + Kotlin.toString(this.quickSummary)) + (', parentTagId=' + Kotlin.toString(this.parentTagId)) + (', id=' + Kotlin.toString(this.id)) + ')';
  };
  Tag.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.label) | 0;
    result = result * 31 + Kotlin.hashCode(this.dataSetId) | 0;
    result = result * 31 + Kotlin.hashCode(this.quickSummary) | 0;
    result = result * 31 + Kotlin.hashCode(this.parentTagId) | 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    return result;
  };
  Tag.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.label, other.label) && Kotlin.equals(this.dataSetId, other.dataSetId) && Kotlin.equals(this.quickSummary, other.quickSummary) && Kotlin.equals(this.parentTagId, other.parentTagId) && Kotlin.equals(this.id, other.id)))));
  };
  function Option(label, dataSetId, quickSummary, url, id) {
    if (quickSummary === void 0)
      quickSummary = null;
    if (url === void 0)
      url = null;
    if (id === void 0)
      id = null;
    this.label = label;
    this.dataSetId = dataSetId;
    this.quickSummary = quickSummary;
    this.url = url;
    this.id = id;
    var tmp$;
    this.truncatedQuickSummary = (tmp$ = this.quickSummary) != null ? truncate(tmp$, 70) : null;
  }
  Option.prototype.getID = function () {
    return this.id;
  };
  Option.prototype.withID_83a66n$ = function (id) {
    return this.copy_hj1ieq$(void 0, void 0, void 0, void 0, id);
  };
  Object.defineProperty(Option.prototype, 'truncatedLabel', {
    get: function () {
      return truncate(this.label, 50);
    }
  });
  Option.prototype.containsAllLowerCaseSubstrings_mhpeer$ = function (lowerCaseSubstrings) {
    var all$result;
    all$break: do {
      var tmp$;
      if (Kotlin.isType(lowerCaseSubstrings, Collection) && lowerCaseSubstrings.isEmpty()) {
        all$result = true;
        break all$break;
      }
      tmp$ = lowerCaseSubstrings.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var tmp$_0 = contains_0(this.label.toLowerCase(), element);
        if (!tmp$_0) {
          var tmp$_1 = this.quickSummary != null;
          if (tmp$_1) {
            tmp$_1 = contains_0(this.quickSummary.toLowerCase(), element);
          }
          tmp$_0 = tmp$_1;
        }
        if (!tmp$_0) {
          all$result = false;
          break all$break;
        }
      }
      all$result = true;
    }
     while (false);
    return all$result;
  };
  Option.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Option',
    interfaces: [WithID]
  };
  Option.prototype.component1 = function () {
    return this.label;
  };
  Option.prototype.component2 = function () {
    return this.dataSetId;
  };
  Option.prototype.component3 = function () {
    return this.quickSummary;
  };
  Option.prototype.component4 = function () {
    return this.url;
  };
  Option.prototype.component5 = function () {
    return this.id;
  };
  Option.prototype.copy_hj1ieq$ = function (label, dataSetId, quickSummary, url, id) {
    return new Option(label === void 0 ? this.label : label, dataSetId === void 0 ? this.dataSetId : dataSetId, quickSummary === void 0 ? this.quickSummary : quickSummary, url === void 0 ? this.url : url, id === void 0 ? this.id : id);
  };
  Option.prototype.toString = function () {
    return 'Option(label=' + Kotlin.toString(this.label) + (', dataSetId=' + Kotlin.toString(this.dataSetId)) + (', quickSummary=' + Kotlin.toString(this.quickSummary)) + (', url=' + Kotlin.toString(this.url)) + (', id=' + Kotlin.toString(this.id)) + ')';
  };
  Option.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.label) | 0;
    result = result * 31 + Kotlin.hashCode(this.dataSetId) | 0;
    result = result * 31 + Kotlin.hashCode(this.quickSummary) | 0;
    result = result * 31 + Kotlin.hashCode(this.url) | 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    return result;
  };
  Option.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.label, other.label) && Kotlin.equals(this.dataSetId, other.dataSetId) && Kotlin.equals(this.quickSummary, other.quickSummary) && Kotlin.equals(this.url, other.url) && Kotlin.equals(this.id, other.id)))));
  };
  function truncate($receiver, maxLength) {
    return $receiver.length > maxLength ? take($receiver, maxLength) + '...' : $receiver;
  }
  function get_opposite($receiver) {
    return toByte(100 - $receiver);
  }
  function toPercentage($receiver) {
    return $receiver ? 100 : 0;
  }
  function OptionTag(optionId, tagId, percentage, dataSetId, id) {
    if (id === void 0)
      id = null;
    this.optionId = optionId;
    this.tagId = tagId;
    this.percentage = percentage;
    this.dataSetId = dataSetId;
    this.id = id;
  }
  OptionTag.prototype.getID = function () {
    return this.id;
  };
  OptionTag.prototype.withID_83a66n$ = function (id) {
    return this.copy_jags3e$(void 0, void 0, void 0, void 0, id);
  };
  Object.defineProperty(OptionTag.prototype, 'idPair', {
    get: function () {
      return new OptionTagIdPair(this.optionId, this.tagId);
    }
  });
  Object.defineProperty(OptionTag.prototype, 'tagValue', {
    get: function () {
      return new TagValue(this.tagId, this.percentage);
    }
  });
  OptionTag.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionTag',
    interfaces: [WithID]
  };
  OptionTag.prototype.component1 = function () {
    return this.optionId;
  };
  OptionTag.prototype.component2 = function () {
    return this.tagId;
  };
  OptionTag.prototype.component3 = function () {
    return this.percentage;
  };
  OptionTag.prototype.component4 = function () {
    return this.dataSetId;
  };
  OptionTag.prototype.component5 = function () {
    return this.id;
  };
  OptionTag.prototype.copy_jags3e$ = function (optionId, tagId, percentage, dataSetId, id) {
    return new OptionTag(optionId === void 0 ? this.optionId : optionId, tagId === void 0 ? this.tagId : tagId, percentage === void 0 ? this.percentage : percentage, dataSetId === void 0 ? this.dataSetId : dataSetId, id === void 0 ? this.id : id);
  };
  OptionTag.prototype.toString = function () {
    return 'OptionTag(optionId=' + Kotlin.toString(this.optionId) + (', tagId=' + Kotlin.toString(this.tagId)) + (', percentage=' + Kotlin.toString(this.percentage)) + (', dataSetId=' + Kotlin.toString(this.dataSetId)) + (', id=' + Kotlin.toString(this.id)) + ')';
  };
  OptionTag.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.optionId) | 0;
    result = result * 31 + Kotlin.hashCode(this.tagId) | 0;
    result = result * 31 + Kotlin.hashCode(this.percentage) | 0;
    result = result * 31 + Kotlin.hashCode(this.dataSetId) | 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    return result;
  };
  OptionTag.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.optionId, other.optionId) && Kotlin.equals(this.tagId, other.tagId) && Kotlin.equals(this.percentage, other.percentage) && Kotlin.equals(this.dataSetId, other.dataSetId) && Kotlin.equals(this.id, other.id)))));
  };
  function OptionTagIdPair(optionId, tagId) {
    this.optionId = optionId;
    this.tagId = tagId;
  }
  OptionTagIdPair.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionTagIdPair',
    interfaces: []
  };
  OptionTagIdPair.prototype.component1 = function () {
    return this.optionId;
  };
  OptionTagIdPair.prototype.component2 = function () {
    return this.tagId;
  };
  OptionTagIdPair.prototype.copy_glzj1f$ = function (optionId, tagId) {
    return new OptionTagIdPair(optionId === void 0 ? this.optionId : optionId, tagId === void 0 ? this.tagId : tagId);
  };
  OptionTagIdPair.prototype.toString = function () {
    return 'OptionTagIdPair(optionId=' + Kotlin.toString(this.optionId) + (', tagId=' + Kotlin.toString(this.tagId)) + ')';
  };
  OptionTagIdPair.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.optionId) | 0;
    result = result * 31 + Kotlin.hashCode(this.tagId) | 0;
    return result;
  };
  OptionTagIdPair.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.optionId, other.optionId) && Kotlin.equals(this.tagId, other.tagId)))));
  };
  function TagValue(tagId, percentage) {
    this.tagId = tagId;
    this.percentage = percentage;
  }
  Object.defineProperty(TagValue.prototype, 'not', {
    get: function () {
      return new TagValue(this.tagId, get_opposite(this.percentage));
    }
  });
  TagValue.prototype.toTagCriteria_6taknv$ = function (preference) {
    return new TagCriteria(this.tagId, this.percentage >= 50, preference);
  };
  TagValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagValue',
    interfaces: []
  };
  TagValue.prototype.component1 = function () {
    return this.tagId;
  };
  TagValue.prototype.component2 = function () {
    return this.percentage;
  };
  TagValue.prototype.copy_nr3614$ = function (tagId, percentage) {
    return new TagValue(tagId === void 0 ? this.tagId : tagId, percentage === void 0 ? this.percentage : percentage);
  };
  TagValue.prototype.toString = function () {
    return 'TagValue(tagId=' + Kotlin.toString(this.tagId) + (', percentage=' + Kotlin.toString(this.percentage)) + ')';
  };
  TagValue.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.tagId) | 0;
    result = result * 31 + Kotlin.hashCode(this.percentage) | 0;
    return result;
  };
  TagValue.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.tagId, other.tagId) && Kotlin.equals(this.percentage, other.percentage)))));
  };
  function OptionTagByOptionTagIdPair(optionTagIdPair) {
    this.optionTagIdPair = optionTagIdPair;
  }
  OptionTagByOptionTagIdPair.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = entity.idPair) != null ? tmp$.equals(this.optionTagIdPair) : null;
  };
  OptionTagByOptionTagIdPair.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionTagByOptionTagIdPair',
    interfaces: [RepositoryCriteria]
  };
  OptionTagByOptionTagIdPair.prototype.component1 = function () {
    return this.optionTagIdPair;
  };
  OptionTagByOptionTagIdPair.prototype.copy_es32k7$ = function (optionTagIdPair) {
    return new OptionTagByOptionTagIdPair(optionTagIdPair === void 0 ? this.optionTagIdPair : optionTagIdPair);
  };
  OptionTagByOptionTagIdPair.prototype.toString = function () {
    return 'OptionTagByOptionTagIdPair(optionTagIdPair=' + Kotlin.toString(this.optionTagIdPair) + ')';
  };
  OptionTagByOptionTagIdPair.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.optionTagIdPair) | 0;
    return result;
  };
  OptionTagByOptionTagIdPair.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.optionTagIdPair, other.optionTagIdPair))));
  };
  function TagTag(primaryTagId, tagId, percentage, dataSetId, id) {
    if (id === void 0)
      id = null;
    this.primaryTagId = primaryTagId;
    this.tagId = tagId;
    this.percentage = percentage;
    this.dataSetId = dataSetId;
    this.id = id;
  }
  TagTag.prototype.getID = function () {
    return this.id;
  };
  TagTag.prototype.withID_83a66n$ = function (id) {
    return this.copy_r7msds$(void 0, void 0, void 0, void 0, id);
  };
  Object.defineProperty(TagTag.prototype, 'idPair', {
    get: function () {
      return new TagTagIdPair(this.primaryTagId, this.tagId);
    }
  });
  Object.defineProperty(TagTag.prototype, 'tagValue', {
    get: function () {
      return new TagValue(this.tagId, this.percentage);
    }
  });
  TagTag.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagTag',
    interfaces: [WithID]
  };
  TagTag.prototype.component1 = function () {
    return this.primaryTagId;
  };
  TagTag.prototype.component2 = function () {
    return this.tagId;
  };
  TagTag.prototype.component3 = function () {
    return this.percentage;
  };
  TagTag.prototype.component4 = function () {
    return this.dataSetId;
  };
  TagTag.prototype.component5 = function () {
    return this.id;
  };
  TagTag.prototype.copy_r7msds$ = function (primaryTagId, tagId, percentage, dataSetId, id) {
    return new TagTag(primaryTagId === void 0 ? this.primaryTagId : primaryTagId, tagId === void 0 ? this.tagId : tagId, percentage === void 0 ? this.percentage : percentage, dataSetId === void 0 ? this.dataSetId : dataSetId, id === void 0 ? this.id : id);
  };
  TagTag.prototype.toString = function () {
    return 'TagTag(primaryTagId=' + Kotlin.toString(this.primaryTagId) + (', tagId=' + Kotlin.toString(this.tagId)) + (', percentage=' + Kotlin.toString(this.percentage)) + (', dataSetId=' + Kotlin.toString(this.dataSetId)) + (', id=' + Kotlin.toString(this.id)) + ')';
  };
  TagTag.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.primaryTagId) | 0;
    result = result * 31 + Kotlin.hashCode(this.tagId) | 0;
    result = result * 31 + Kotlin.hashCode(this.percentage) | 0;
    result = result * 31 + Kotlin.hashCode(this.dataSetId) | 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    return result;
  };
  TagTag.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.primaryTagId, other.primaryTagId) && Kotlin.equals(this.tagId, other.tagId) && Kotlin.equals(this.percentage, other.percentage) && Kotlin.equals(this.dataSetId, other.dataSetId) && Kotlin.equals(this.id, other.id)))));
  };
  function TagTagIdPair(primaryTagId, tagId) {
    this.primaryTagId = primaryTagId;
    this.tagId = tagId;
  }
  TagTagIdPair.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagTagIdPair',
    interfaces: []
  };
  TagTagIdPair.prototype.component1 = function () {
    return this.primaryTagId;
  };
  TagTagIdPair.prototype.component2 = function () {
    return this.tagId;
  };
  TagTagIdPair.prototype.copy_mp1ia4$ = function (primaryTagId, tagId) {
    return new TagTagIdPair(primaryTagId === void 0 ? this.primaryTagId : primaryTagId, tagId === void 0 ? this.tagId : tagId);
  };
  TagTagIdPair.prototype.toString = function () {
    return 'TagTagIdPair(primaryTagId=' + Kotlin.toString(this.primaryTagId) + (', tagId=' + Kotlin.toString(this.tagId)) + ')';
  };
  TagTagIdPair.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.primaryTagId) | 0;
    result = result * 31 + Kotlin.hashCode(this.tagId) | 0;
    return result;
  };
  TagTagIdPair.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.primaryTagId, other.primaryTagId) && Kotlin.equals(this.tagId, other.tagId)))));
  };
  function TagTagByTagTagIdPair(tagTagIdPair) {
    this.tagTagIdPair = tagTagIdPair;
  }
  TagTagByTagTagIdPair.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = entity.idPair) != null ? tmp$.equals(this.tagTagIdPair) : null;
  };
  TagTagByTagTagIdPair.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagTagByTagTagIdPair',
    interfaces: [RepositoryCriteria]
  };
  TagTagByTagTagIdPair.prototype.component1 = function () {
    return this.tagTagIdPair;
  };
  TagTagByTagTagIdPair.prototype.copy_42n4bc$ = function (tagTagIdPair) {
    return new TagTagByTagTagIdPair(tagTagIdPair === void 0 ? this.tagTagIdPair : tagTagIdPair);
  };
  TagTagByTagTagIdPair.prototype.toString = function () {
    return 'TagTagByTagTagIdPair(tagTagIdPair=' + Kotlin.toString(this.tagTagIdPair) + ')';
  };
  TagTagByTagTagIdPair.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.tagTagIdPair) | 0;
    return result;
  };
  TagTagByTagTagIdPair.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.tagTagIdPair, other.tagTagIdPair))));
  };
  function MatchScore(probability, missingTagCount) {
    MatchScore$Companion_getInstance();
    this.probability = probability;
    this.missingTagCount = missingTagCount;
  }
  Object.defineProperty(MatchScore.prototype, 'isMatch', {
    get: function () {
      return TagCriteria$Companion_getInstance().isMatchScore_mx4ult$(this.probability);
    }
  });
  Object.defineProperty(MatchScore.prototype, 'isMatchWithAllTags', {
    get: function () {
      return this.isMatch && !this.isMissingTags;
    }
  });
  Object.defineProperty(MatchScore.prototype, 'isMatchWithMissingTags', {
    get: function () {
      return this.isMatch && this.isMissingTags;
    }
  });
  Object.defineProperty(MatchScore.prototype, 'isNonMatch', {
    get: function () {
      return !this.isMatch;
    }
  });
  Object.defineProperty(MatchScore.prototype, 'isMissingTags', {
    get: function () {
      return this.missingTagCount > 0;
    }
  });
  MatchScore.prototype.not = function () {
    return new MatchScore(1.0 - this.probability, this.missingTagCount);
  };
  function MatchScore$Companion() {
    MatchScore$Companion_instance = this;
    this.defaultMatchScore = new MatchScore(TagCriteria$Companion_getInstance().defaultProbability, 1);
    this.defaultMatchScoreForPreference = new MatchScore(1.0 + TagCriteria$Companion_getInstance().defaultProbability * TagCriteria$Companion_getInstance().preferenceMultiplier, 1);
  }
  MatchScore$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var MatchScore$Companion_instance = null;
  function MatchScore$Companion_getInstance() {
    if (MatchScore$Companion_instance === null) {
      new MatchScore$Companion();
    }
    return MatchScore$Companion_instance;
  }
  MatchScore.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MatchScore',
    interfaces: []
  };
  MatchScore.prototype.component1 = function () {
    return this.probability;
  };
  MatchScore.prototype.component2 = function () {
    return this.missingTagCount;
  };
  MatchScore.prototype.copy_vjorfl$ = function (probability, missingTagCount) {
    return new MatchScore(probability === void 0 ? this.probability : probability, missingTagCount === void 0 ? this.missingTagCount : missingTagCount);
  };
  MatchScore.prototype.toString = function () {
    return 'MatchScore(probability=' + Kotlin.toString(this.probability) + (', missingTagCount=' + Kotlin.toString(this.missingTagCount)) + ')';
  };
  MatchScore.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.probability) | 0;
    result = result * 31 + Kotlin.hashCode(this.missingTagCount) | 0;
    return result;
  };
  MatchScore.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.probability, other.probability) && Kotlin.equals(this.missingTagCount, other.missingTagCount)))));
  };
  function allOf($receiver) {
    var tmp$;
    var accumulator = 1.0;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      accumulator = accumulator * element.probability;
    }
    var probability = accumulator;
    var tmp$_0;
    var sum = 0;
    tmp$_0 = $receiver.iterator();
    while (tmp$_0.hasNext()) {
      var element_0 = tmp$_0.next();
      sum = sum + element_0.missingTagCount | 0;
    }
    return new MatchScore(probability, sum);
  }
  var Math_0 = Math;
  function mostOpinionated($receiver) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var minBy$result;
    minBy$break: do {
      var iterator = $receiver.iterator();
      if (!iterator.hasNext()) {
        minBy$result = null;
        break minBy$break;
      }
      var minElem = iterator.next();
      var minValue = minElem.missingTagCount;
      while (iterator.hasNext()) {
        var e = iterator.next();
        var v = e.missingTagCount;
        if (Kotlin.compareTo(minValue, v) > 0) {
          minElem = e;
          minValue = v;
        }
      }
      minBy$result = minElem;
    }
     while (false);
    var missingTagCount = (tmp$_0 = (tmp$ = minBy$result) != null ? tmp$.missingTagCount : null) != null ? tmp$_0 : 1;
    var maxBy$result;
    maxBy$break: do {
      var iterator_0 = $receiver.iterator();
      if (!iterator_0.hasNext()) {
        maxBy$result = null;
        break maxBy$break;
      }
      var maxElem = iterator_0.next();
      var x = maxElem.probability - 0.5;
      var maxValue = Math_0.abs(x);
      while (iterator_0.hasNext()) {
        var e_0 = iterator_0.next();
        var x_0 = e_0.probability - 0.5;
        var v_0 = Math_0.abs(x_0);
        if (Kotlin.compareTo(maxValue, v_0) < 0) {
          maxElem = e_0;
          maxValue = v_0;
        }
      }
      maxBy$result = maxElem;
    }
     while (false);
    return (tmp$_2 = (tmp$_1 = maxBy$result) != null ? tmp$_1.copy_vjorfl$(void 0, missingTagCount) : null) != null ? tmp$_2 : MatchScore$Companion_getInstance().defaultMatchScore;
  }
  function TagValueCriteria() {
  }
  Object.defineProperty(TagValueCriteria.prototype, 'pivotalTagId', {
    get: function () {
      return this.pivotalTagCriteria.tagId;
    }
  });
  TagValueCriteria.prototype.findDecidingKeyTagValue_onfp55$ = function (tagValues) {
    var tmp$, tmp$_0;
    var destination = ArrayList_init(collectionSizeOrDefault(tagValues, 10));
    var tmp$_1;
    tmp$_1 = tagValues.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      destination.add_11rb$(new Pair(item, this.matchScore_onfp55$(listOf_0(item))));
    }
    var matchingTagValues = destination;
    var destination_0 = ArrayList_init();
    var tmp$_2;
    tmp$_2 = matchingTagValues.iterator();
    while (tmp$_2.hasNext()) {
      var element = tmp$_2.next();
      if (element.second.isNonMatch)
        destination_0.add_11rb$(element);
    }
    var nonMatchTagValues = destination_0;
    if (!nonMatchTagValues.isEmpty()) {
      tmp$_0 = first(nonMatchTagValues).first;
    }
     else {
      var destination_1 = ArrayList_init();
      var tmp$_3;
      tmp$_3 = matchingTagValues.iterator();
      while (tmp$_3.hasNext()) {
        var element_0 = tmp$_3.next();
        if (!element_0.second.isMissingTags)
          destination_1.add_11rb$(element_0);
      }
      var tagValuesWithScores = destination_1;
      var maxBy$result;
      maxBy$break: do {
        var iterator = tagValuesWithScores.iterator();
        if (!iterator.hasNext()) {
          maxBy$result = null;
          break maxBy$break;
        }
        var maxElem = iterator.next();
        var x = maxElem.second.probability - 0.5;
        var maxValue = Math_0.abs(x);
        while (iterator.hasNext()) {
          var e = iterator.next();
          var x_0 = e.second.probability - 0.5;
          var v = Math_0.abs(x_0);
          if (Kotlin.compareTo(maxValue, v) < 0) {
            maxElem = e;
            maxValue = v;
          }
        }
        maxBy$result = maxElem;
      }
       while (false);
      tmp$_0 = (tmp$ = maxBy$result) != null ? tmp$.first : null;
    }
    return tmp$_0;
  };
  TagValueCriteria.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'TagValueCriteria',
    interfaces: []
  };
  function TagCriteria(tagId, value, preference) {
    TagCriteria$Companion_getInstance();
    if (preference === void 0)
      preference = false;
    this.tagId = tagId;
    this.value = value;
    this.preference = preference;
  }
  Object.defineProperty(TagCriteria.prototype, 'pivotalTagCriteria', {
    get: function () {
      return this;
    }
  });
  TagCriteria.prototype.matchScore_onfp55$ = function (tagValues) {
    var tmp$, tmp$_0;
    var tagCriteriaValue = this.value;
    var tmp$_1;
    var firstOrNull$result;
    firstOrNull$break: do {
      var tmp$_2;
      tmp$_2 = tagValues.iterator();
      while (tmp$_2.hasNext()) {
        var element = tmp$_2.next();
        var tmp$_3;
        if ((tmp$_3 = element.tagId) != null ? tmp$_3.equals(this.tagId) : null) {
          firstOrNull$result = element;
          break firstOrNull$break;
        }
      }
      firstOrNull$result = null;
    }
     while (false);
    if ((tmp$ = firstOrNull$result) != null) {
      var matchProbability = (100 - abs(tmp$.percentage - toPercentage(tagCriteriaValue)) | 0) / 100.0;
      tmp$_1 = new MatchScore(this.adjustProbabilityForPreference_0(matchProbability), 0);
    }
     else
      tmp$_1 = null;
    return (tmp$_0 = tmp$_1) != null ? tmp$_0 : this.preference ? MatchScore$Companion_getInstance().defaultMatchScoreForPreference : MatchScore$Companion_getInstance().defaultMatchScore;
  };
  TagCriteria.prototype.adjustProbabilityForPreference_0 = function (matchProbability) {
    return this.preference ? 1.0 + TagCriteria$Companion_getInstance().preferenceMultiplier * matchProbability : matchProbability;
  };
  Object.defineProperty(TagCriteria.prototype, 'tagValue', {
    get: function () {
      return new TagValue(this.tagId, toPercentage(this.value));
    }
  });
  function TagCriteria$Companion() {
    TagCriteria$Companion_instance = this;
    this.preferenceMultiplier = 0.001;
    this.defaultProbability = 0.5;
  }
  TagCriteria$Companion.prototype.isMatchScore_mx4ult$ = function (score) {
    return score > 0.0;
  };
  TagCriteria$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var TagCriteria$Companion_instance = null;
  function TagCriteria$Companion_getInstance() {
    if (TagCriteria$Companion_instance === null) {
      new TagCriteria$Companion();
    }
    return TagCriteria$Companion_instance;
  }
  TagCriteria.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagCriteria',
    interfaces: [TagValueCriteria]
  };
  TagCriteria.prototype.component1 = function () {
    return this.tagId;
  };
  TagCriteria.prototype.component2 = function () {
    return this.value;
  };
  TagCriteria.prototype.component3 = function () {
    return this.preference;
  };
  TagCriteria.prototype.copy_shiqq7$ = function (tagId, value, preference) {
    return new TagCriteria(tagId === void 0 ? this.tagId : tagId, value === void 0 ? this.value : value, preference === void 0 ? this.preference : preference);
  };
  TagCriteria.prototype.toString = function () {
    return 'TagCriteria(tagId=' + Kotlin.toString(this.tagId) + (', value=' + Kotlin.toString(this.value)) + (', preference=' + Kotlin.toString(this.preference)) + ')';
  };
  TagCriteria.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.tagId) | 0;
    result = result * 31 + Kotlin.hashCode(this.value) | 0;
    result = result * 31 + Kotlin.hashCode(this.preference) | 0;
    return result;
  };
  TagCriteria.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.tagId, other.tagId) && Kotlin.equals(this.value, other.value) && Kotlin.equals(this.preference, other.preference)))));
  };
  function DisallowedTagCriteria(tagId, value, preference) {
    return new DisallowedTagCriteria_0(new TagCriteria(tagId, value, preference));
  }
  function min(float1, float2) {
    return float1 !== float1 ? float1 : float1 === 0.0 && float2 === 0.0 ? float2 : float1 <= float2 ? float1 : float2;
  }
  function DisallowedTagCriteria_0(criteria) {
    this.criteria = criteria;
  }
  Object.defineProperty(DisallowedTagCriteria_0.prototype, 'pivotalTagCriteria', {
    get: function () {
      return this.criteria.pivotalTagCriteria;
    }
  });
  DisallowedTagCriteria_0.prototype.matchScore_onfp55$ = function (tagValues) {
    var tmp$;
    var matchScore = this.criteria.matchScore_onfp55$(tagValues);
    if (matchScore.isMatch) {
      tmp$ = new MatchScore(1.0 - matchScore.probability, matchScore.missingTagCount);
    }
     else {
      tmp$ = MatchScore$Companion_getInstance().defaultMatchScore;
    }
    return tmp$;
  };
  DisallowedTagCriteria_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DisallowedTagCriteria',
    interfaces: [TagValueCriteria]
  };
  DisallowedTagCriteria_0.prototype.component1 = function () {
    return this.criteria;
  };
  DisallowedTagCriteria_0.prototype.copy_w5qb47$ = function (criteria) {
    return new DisallowedTagCriteria_0(criteria === void 0 ? this.criteria : criteria);
  };
  DisallowedTagCriteria_0.prototype.toString = function () {
    return 'DisallowedTagCriteria(criteria=' + Kotlin.toString(this.criteria) + ')';
  };
  DisallowedTagCriteria_0.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.criteria) | 0;
    return result;
  };
  DisallowedTagCriteria_0.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.criteria, other.criteria))));
  };
  function anyOf(tagCriteriaSet) {
    return new AnyOfTagCriteria(toSet(tagCriteriaSet));
  }
  function AnyOfTagCriteria(tagCriteriaSet) {
    this.tagCriteriaSet = tagCriteriaSet;
  }
  Object.defineProperty(AnyOfTagCriteria.prototype, 'pivotalTagCriteria', {
    get: function () {
      return first_0(this.tagCriteriaSet).pivotalTagCriteria;
    }
  });
  AnyOfTagCriteria.prototype.matchScore_onfp55$ = function (tagValues) {
    var $receiver = this.tagCriteriaSet;
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(item.matchScore_onfp55$(tagValues));
    }
    return mostOpinionated(destination);
  };
  AnyOfTagCriteria.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AnyOfTagCriteria',
    interfaces: [TagValueCriteria]
  };
  AnyOfTagCriteria.prototype.component1 = function () {
    return this.tagCriteriaSet;
  };
  AnyOfTagCriteria.prototype.copy_8lq8sw$ = function (tagCriteriaSet) {
    return new AnyOfTagCriteria(tagCriteriaSet === void 0 ? this.tagCriteriaSet : tagCriteriaSet);
  };
  AnyOfTagCriteria.prototype.toString = function () {
    return 'AnyOfTagCriteria(tagCriteriaSet=' + Kotlin.toString(this.tagCriteriaSet) + ')';
  };
  AnyOfTagCriteria.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.tagCriteriaSet) | 0;
    return result;
  };
  AnyOfTagCriteria.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.tagCriteriaSet, other.tagCriteriaSet))));
  };
  function abs_0(float) {
    return float < 0 ? -float : float;
  }
  function abs_1(int) {
    return int < 0 ? -int : int;
  }
  function matchScore($receiver, tagValues) {
    var tmp$;
    var first = ArrayList_init();
    var second = ArrayList_init();
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (element.pivotalTagCriteria.preference) {
        first.add_11rb$(element);
      }
       else {
        second.add_11rb$(element);
      }
    }
    var tmp$_0 = new Pair(first, second);
    var preferenceCriteria = tmp$_0.component1()
    , requiredCriteria = tmp$_0.component2();
    var destination = ArrayList_init(collectionSizeOrDefault(requiredCriteria, 10));
    var tmp$_1;
    tmp$_1 = requiredCriteria.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      destination.add_11rb$(item.matchScore_onfp55$(tagValues));
    }
    var requiredProbability = allOf(destination);
    var destination_0 = ArrayList_init(collectionSizeOrDefault(preferenceCriteria, 10));
    var tmp$_2;
    tmp$_2 = preferenceCriteria.iterator();
    while (tmp$_2.hasNext()) {
      var item_0 = tmp$_2.next();
      destination_0.add_11rb$(item_0.matchScore_onfp55$(tagValues));
    }
    var preferenceMatchScores = destination_0;
    var destination_1 = ArrayList_init(collectionSizeOrDefault(preferenceMatchScores, 10));
    var tmp$_3;
    tmp$_3 = preferenceMatchScores.iterator();
    while (tmp$_3.hasNext()) {
      var item_1 = tmp$_3.next();
      destination_1.add_11rb$(item_1.probability * TagCriteria$Companion_getInstance().preferenceMultiplier);
    }
    var preferenceBonus = sum(destination_1);
    var tmp$_4 = requiredProbability.missingTagCount;
    var destination_2 = ArrayList_init(collectionSizeOrDefault(preferenceMatchScores, 10));
    var tmp$_5;
    tmp$_5 = preferenceMatchScores.iterator();
    while (tmp$_5.hasNext()) {
      var item_2 = tmp$_5.next();
      destination_2.add_11rb$(item_2.missingTagCount);
    }
    var missingTagCount = tmp$_4 + sum_0(destination_2) | 0;
    return new MatchScore(requiredProbability.probability + preferenceBonus, missingTagCount);
  }
  function dataSet($receiver, label, init) {
    var dataSetId = $receiver.add_wc5hdh$(new DataSet(label));
    var dataSetContext = new DataSetContext($receiver, dataSetId);
    init(dataSetContext);
    return dataSetId;
  }
  var LinkedHashSet_init = Kotlin.kotlin.collections.LinkedHashSet_init_287e2$;
  function DataSetContext(modelStorage, dataSetId) {
    this.modelStorage = modelStorage;
    this.dataSetId = dataSetId;
    this.allTagIds = LinkedHashSet_init();
  }
  DataSetContext.prototype.tag_jyasbz$ = function (label, quickSummary) {
    if (quickSummary === void 0)
      quickSummary = null;
    var tagId = this.modelStorage.add_jkj7jr$(new Tag(label, this.dataSetId, quickSummary));
    this.allTagIds.add_11rb$(tagId);
    return tagId;
  };
  DataSetContext.prototype.action_579qba$ = function (label, tagIds, quickSummary, init) {
    if (quickSummary === void 0)
      quickSummary = null;
    if (init === void 0)
      init = null;
    var tmp$;
    var actionContext = new OptionContext();
    init != null ? init(actionContext) : null;
    var optionId = this.modelStorage.add_2oncaw$(new Option(label, this.dataSetId, quickSummary, actionContext.url));
    tmp$ = this.allTagIds.iterator();
    while (tmp$.hasNext()) {
      var tagId = tmp$.next();
      this.modelStorage.add_1kxy0y$(new OptionTag(optionId, tagId, toPercentage(tagIds.contains_11rb$(tagId)), this.dataSetId));
    }
    return optionId;
  };
  DataSetContext.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DataSetContext',
    interfaces: []
  };
  function OptionContext() {
    this.url = null;
  }
  OptionContext.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionContext',
    interfaces: []
  };
  function setPercentage_0($receiver, optionTagWithoutId) {
    var tmp$;
    var firstMatch = firstOrNull_0($receiver.list_fmyu0x$(new OptionTagByOptionTagIdPair(optionTagWithoutId.idPair)));
    if ((firstMatch != null ? firstMatch.percentage : null) !== optionTagWithoutId.percentage) {
      var optionTagToSave = (tmp$ = firstMatch != null ? firstMatch.copy_jags3e$(void 0, void 0, optionTagWithoutId.percentage) : null) != null ? tmp$ : optionTagWithoutId;
      $receiver.save_tf6uce$(firstMatch, optionTagToSave);
    }
  }
  function remove($receiver, optionTagIdPair) {
    var tmp$;
    tmp$ = $receiver.list_fmyu0x$(new OptionTagByOptionTagIdPair(optionTagIdPair)).iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      $receiver.remove_g5e3t3$(element);
    }
  }
  function setValue($receiver, tagTagWithoutId) {
    var tmp$;
    var firstMatch = firstOrNull_0($receiver.list_fmyu0x$(new TagTagByTagTagIdPair(tagTagWithoutId.idPair)));
    if ((firstMatch != null ? firstMatch.percentage : null) !== tagTagWithoutId.percentage) {
      var optionTagToSave = (tmp$ = firstMatch != null ? firstMatch.copy_r7msds$(void 0, void 0, tagTagWithoutId.percentage) : null) != null ? tmp$ : tagTagWithoutId;
      return $receiver.save_tf6uce$(firstMatch, optionTagToSave);
    }
     else {
      return ensureNotNull(firstMatch.id);
    }
  }
  function remove_0($receiver, tagTagIdPair) {
    var tmp$;
    tmp$ = $receiver.list_fmyu0x$(new TagTagByTagTagIdPair(tagTagIdPair)).iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      $receiver.remove_g5e3t3$(element);
    }
  }
  function ModelStorage() {
  }
  ModelStorage.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ModelStorage',
    interfaces: []
  };
  function findOrCreate($receiver, label, dataSetId) {
    var tmp$;
    var matchingTagId = (tmp$ = firstOrNull_0($receiver.list_fmyu0x$(new TagByLabel(label, dataSetId)))) != null ? tmp$.id : null;
    return matchingTagId != null ? matchingTagId : $receiver.save_tf6uce$(null, new Tag(label, dataSetId));
  }
  function getImpliedTagValues($receiver, tagValue) {
    var result = mutableSetOf([tagValue]);
    addImpliedTagValues($receiver, tagValue, result);
    result.remove_11rb$(tagValue);
    return result;
  }
  function addImpliedTagValues($receiver, tagValue, result) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (tagValue.percentage > 50) {
      tmp$ = $receiver.list_fmyu0x$(new TagTagByPrimaryTagId(tagValue.tagId)).iterator();
      while (tmp$.hasNext()) {
        var nextTagTag = tmp$.next();
        if (!((tmp$_0 = nextTagTag.tagId) != null ? tmp$_0.equals(tagValue.tagId) : null)) {
          var impliedTagValue = nextTagTag.tagValue;
          if (result.add_11rb$(impliedTagValue)) {
            addImpliedTagValues($receiver, impliedTagValue, result);
          }
        }
      }
    }
    tmp$_1 = $receiver.list_fmyu0x$(new TagTagByTagValue(tagValue.not)).iterator();
    while (tmp$_1.hasNext()) {
      var nextTagTag_0 = tmp$_1.next();
      if (!((tmp$_2 = nextTagTag_0.primaryTagId) != null ? tmp$_2.equals(tagValue.tagId) : null)) {
        var impliedTag = new TagValue(nextTagTag_0.primaryTagId, 0);
        if (result.add_11rb$(impliedTag)) {
          addImpliedTagValues($receiver, impliedTag, result);
        }
      }
    }
  }
  function getTagValuesThatImplyTagValue($receiver, tagValue) {
    var result = mutableSetOf([tagValue]);
    addTagValuesThatImplyTagValue($receiver, tagValue, result);
    result.remove_11rb$(tagValue);
    return result;
  }
  function addTagValuesThatImplyTagValue($receiver, tagValue, result) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (tagValue.percentage <= 25) {
      tmp$ = $receiver.list_fmyu0x$(new TagTagByPrimaryTagId(tagValue.tagId)).iterator();
      while (tmp$.hasNext()) {
        var nextTagTag = tmp$.next();
        if (!((tmp$_0 = nextTagTag.tagId) != null ? tmp$_0.equals(tagValue.tagId) : null)) {
          var impliedTagValue = nextTagTag.tagValue.not;
          if (result.add_11rb$(impliedTagValue)) {
            addTagValuesThatImplyTagValue($receiver, impliedTagValue, result);
          }
        }
      }
    }
    tmp$_1 = $receiver.list_fmyu0x$(new TagTagByTagValue(tagValue)).iterator();
    while (tmp$_1.hasNext()) {
      var nextTagTag_0 = tmp$_1.next();
      if (!((tmp$_2 = nextTagTag_0.primaryTagId) != null ? tmp$_2.equals(tagValue.tagId) : null)) {
        var implyingTagValue = new TagValue(nextTagTag_0.primaryTagId, 100);
        if (result.add_11rb$(implyingTagValue)) {
          addTagValuesThatImplyTagValue($receiver, implyingTagValue, result);
        }
      }
    }
  }
  function getEffectiveTagCriteria($receiver, requestedTagCriteria) {
    var tmp$, tmp$_0;
    var unanalyzedTagCriteria = mutableListOf([requestedTagCriteria]);
    var tagCriteriaList = LinkedHashSet_init();
    var $receiver_0 = getTagValuesThatImplyTagValue($receiver, requestedTagCriteria.tagValue.not);
    var destination = ArrayList_init(collectionSizeOrDefault($receiver_0, 10));
    var tmp$_1;
    tmp$_1 = $receiver_0.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      destination.add_11rb$(item.toTagCriteria_6taknv$(requestedTagCriteria.preference));
    }
    var disallowedCriteriaList = destination;
    do {
      var tagCriteria = unanalyzedTagCriteria.removeAt_za3lpa$(0);
      var added = tagCriteriaList.add_11rb$(tagCriteria);
      if (added) {
        tmp$ = $receiver.list_fmyu0x$(new TagTagByTagValue(tagCriteria.tagValue)).iterator();
        while (tmp$.hasNext()) {
          var tagTag = tmp$.next();
          unanalyzedTagCriteria.add_11rb$(new TagCriteria(tagTag.primaryTagId, true, requestedTagCriteria.preference));
        }
        if (!tagCriteria.value) {
          tmp$_0 = $receiver.list_fmyu0x$(new TagTagByPrimaryTagId(tagCriteria.tagId)).iterator();
          while (tmp$_0.hasNext()) {
            var tagTag_0 = tmp$_0.next();
            unanalyzedTagCriteria.add_11rb$(new TagCriteria(tagTag_0.tagId, get_opposite(tagTag_0.percentage) >= 50, requestedTagCriteria.preference));
          }
        }
      }
      var isNotEmpty$result;
      isNotEmpty$result = !unanalyzedTagCriteria.isEmpty();
    }
     while (isNotEmpty$result);
    var result = toMutableSet(tagCriteriaList);
    var tmp$_2;
    tmp$_2 = disallowedCriteriaList.iterator();
    while (tmp$_2.hasNext()) {
      var element = tmp$_2.next();
      var none$result;
      none$break: do {
        var tmp$_3;
        if (Kotlin.isType(tagCriteriaList, Collection) && tagCriteriaList.isEmpty()) {
          none$result = true;
          break none$break;
        }
        tmp$_3 = tagCriteriaList.iterator();
        while (tmp$_3.hasNext()) {
          var element_0 = tmp$_3.next();
          var tmp$_4;
          if ((tmp$_4 = element_0.tagId) != null ? tmp$_4.equals(element.tagId) : null) {
            none$result = false;
            break none$break;
          }
        }
        none$result = true;
      }
       while (false);
      if (none$result) {
        result.add_11rb$(new DisallowedTagCriteria_0(element));
      }
    }
    return result.size === 1 ? first_0(result) : new AnyOfTagCriteria(result);
  }
  function OptionByDataSetId(dataSetId) {
    this.dataSetId = dataSetId;
  }
  OptionByDataSetId.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = entity.dataSetId) != null ? tmp$.equals(this.dataSetId) : null;
  };
  OptionByDataSetId.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionByDataSetId',
    interfaces: [RepositoryCriteria]
  };
  OptionByDataSetId.prototype.component1 = function () {
    return this.dataSetId;
  };
  OptionByDataSetId.prototype.copy_bmyw6p$ = function (dataSetId) {
    return new OptionByDataSetId(dataSetId === void 0 ? this.dataSetId : dataSetId);
  };
  OptionByDataSetId.prototype.toString = function () {
    return 'OptionByDataSetId(dataSetId=' + Kotlin.toString(this.dataSetId) + ')';
  };
  OptionByDataSetId.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.dataSetId) | 0;
    return result;
  };
  OptionByDataSetId.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.dataSetId, other.dataSetId))));
  };
  function TagByDataSetId(dataSetId) {
    this.dataSetId = dataSetId;
  }
  TagByDataSetId.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = entity.dataSetId) != null ? tmp$.equals(this.dataSetId) : null;
  };
  TagByDataSetId.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagByDataSetId',
    interfaces: [RepositoryCriteria]
  };
  TagByDataSetId.prototype.component1 = function () {
    return this.dataSetId;
  };
  TagByDataSetId.prototype.copy_bmyw6p$ = function (dataSetId) {
    return new TagByDataSetId(dataSetId === void 0 ? this.dataSetId : dataSetId);
  };
  TagByDataSetId.prototype.toString = function () {
    return 'TagByDataSetId(dataSetId=' + Kotlin.toString(this.dataSetId) + ')';
  };
  TagByDataSetId.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.dataSetId) | 0;
    return result;
  };
  TagByDataSetId.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.dataSetId, other.dataSetId))));
  };
  function TagByLabel(label, dataSetId) {
    this.label = label;
    this.dataSetId = dataSetId;
  }
  TagByLabel.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return equals(entity.label, this.label) && ((tmp$ = entity.dataSetId) != null ? tmp$.equals(this.dataSetId) : null);
  };
  TagByLabel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagByLabel',
    interfaces: [RepositoryCriteria]
  };
  TagByLabel.prototype.component1 = function () {
    return this.label;
  };
  TagByLabel.prototype.component2 = function () {
    return this.dataSetId;
  };
  TagByLabel.prototype.copy_misvkx$ = function (label, dataSetId) {
    return new TagByLabel(label === void 0 ? this.label : label, dataSetId === void 0 ? this.dataSetId : dataSetId);
  };
  TagByLabel.prototype.toString = function () {
    return 'TagByLabel(label=' + Kotlin.toString(this.label) + (', dataSetId=' + Kotlin.toString(this.dataSetId)) + ')';
  };
  TagByLabel.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.label) | 0;
    result = result * 31 + Kotlin.hashCode(this.dataSetId) | 0;
    return result;
  };
  TagByLabel.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.label, other.label) && Kotlin.equals(this.dataSetId, other.dataSetId)))));
  };
  function OptionTagByDataSetId(dataSetId) {
    this.dataSetId = dataSetId;
  }
  OptionTagByDataSetId.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = entity.dataSetId) != null ? tmp$.equals(this.dataSetId) : null;
  };
  OptionTagByDataSetId.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionTagByDataSetId',
    interfaces: [RepositoryCriteria]
  };
  OptionTagByDataSetId.prototype.component1 = function () {
    return this.dataSetId;
  };
  OptionTagByDataSetId.prototype.copy_bmyw6p$ = function (dataSetId) {
    return new OptionTagByDataSetId(dataSetId === void 0 ? this.dataSetId : dataSetId);
  };
  OptionTagByDataSetId.prototype.toString = function () {
    return 'OptionTagByDataSetId(dataSetId=' + Kotlin.toString(this.dataSetId) + ')';
  };
  OptionTagByDataSetId.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.dataSetId) | 0;
    return result;
  };
  OptionTagByDataSetId.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.dataSetId, other.dataSetId))));
  };
  function OptionTagIdPairField() {
    OptionTagIdPairField_instance = this;
  }
  OptionTagIdPairField.prototype.invoke_g5e3t3$ = function (entity) {
    return entity.idPair;
  };
  OptionTagIdPairField.prototype.equals = function (other) {
    return Kotlin.isType(other, OptionTagIdPairField);
  };
  OptionTagIdPairField.prototype.hashCode = function () {
    return hashCode(this.toString());
  };
  OptionTagIdPairField.prototype.toString = function () {
    return 'OptionTagIdPairField';
  };
  OptionTagIdPairField.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'OptionTagIdPairField',
    interfaces: [FieldSelector]
  };
  var OptionTagIdPairField_instance = null;
  function OptionTagIdPairField_getInstance() {
    if (OptionTagIdPairField_instance === null) {
      new OptionTagIdPairField();
    }
    return OptionTagIdPairField_instance;
  }
  function TagTagIdPairField() {
    TagTagIdPairField_instance = this;
  }
  TagTagIdPairField.prototype.invoke_g5e3t3$ = function (entity) {
    return entity.idPair;
  };
  TagTagIdPairField.prototype.equals = function (other) {
    return Kotlin.isType(other, TagTagIdPairField);
  };
  TagTagIdPairField.prototype.hashCode = function () {
    return hashCode(this.toString());
  };
  TagTagIdPairField.prototype.toString = function () {
    return 'TagTagIdPairField';
  };
  TagTagIdPairField.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'TagTagIdPairField',
    interfaces: [FieldSelector]
  };
  var TagTagIdPairField_instance = null;
  function TagTagIdPairField_getInstance() {
    if (TagTagIdPairField_instance === null) {
      new TagTagIdPairField();
    }
    return TagTagIdPairField_instance;
  }
  function OptionTagValueField() {
    OptionTagValueField_instance = this;
  }
  OptionTagValueField.prototype.invoke_g5e3t3$ = function (entity) {
    return entity.tagValue;
  };
  OptionTagValueField.prototype.equals = function (other) {
    return Kotlin.isType(other, OptionTagValueField);
  };
  OptionTagValueField.prototype.hashCode = function () {
    return hashCode(this.toString());
  };
  OptionTagValueField.prototype.toString = function () {
    return 'OptionTagValueField';
  };
  OptionTagValueField.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'OptionTagValueField',
    interfaces: [FieldSelector]
  };
  var OptionTagValueField_instance = null;
  function OptionTagValueField_getInstance() {
    if (OptionTagValueField_instance === null) {
      new OptionTagValueField();
    }
    return OptionTagValueField_instance;
  }
  function TagValueField() {
    TagValueField_instance = this;
  }
  TagValueField.prototype.invoke_g5e3t3$ = function (entity) {
    return entity.tagValue;
  };
  TagValueField.prototype.equals = function (other) {
    return Kotlin.isType(other, TagValueField);
  };
  TagValueField.prototype.hashCode = function () {
    return hashCode(this.toString());
  };
  TagValueField.prototype.toString = function () {
    return 'TagValueField';
  };
  TagValueField.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'TagValueField',
    interfaces: [FieldSelector]
  };
  var TagValueField_instance = null;
  function TagValueField_getInstance() {
    if (TagValueField_instance === null) {
      new TagValueField();
    }
    return TagValueField_instance;
  }
  function OptionTagByOptionId(optionId) {
    this.optionId = optionId;
  }
  OptionTagByOptionId.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = this.optionId) != null ? tmp$.equals(entity.optionId) : null;
  };
  OptionTagByOptionId.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionTagByOptionId',
    interfaces: [RepositoryCriteria]
  };
  OptionTagByOptionId.prototype.component1 = function () {
    return this.optionId;
  };
  OptionTagByOptionId.prototype.copy_s47mgy$ = function (optionId) {
    return new OptionTagByOptionId(optionId === void 0 ? this.optionId : optionId);
  };
  OptionTagByOptionId.prototype.toString = function () {
    return 'OptionTagByOptionId(optionId=' + Kotlin.toString(this.optionId) + ')';
  };
  OptionTagByOptionId.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.optionId) | 0;
    return result;
  };
  OptionTagByOptionId.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.optionId, other.optionId))));
  };
  function OptionTagByTagId(tagId) {
    this.tagId = tagId;
  }
  OptionTagByTagId.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = this.tagId) != null ? tmp$.equals(entity.tagId) : null;
  };
  OptionTagByTagId.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionTagByTagId',
    interfaces: [RepositoryCriteria]
  };
  OptionTagByTagId.prototype.component1 = function () {
    return this.tagId;
  };
  OptionTagByTagId.prototype.copy_apv9qp$ = function (tagId) {
    return new OptionTagByTagId(tagId === void 0 ? this.tagId : tagId);
  };
  OptionTagByTagId.prototype.toString = function () {
    return 'OptionTagByTagId(tagId=' + Kotlin.toString(this.tagId) + ')';
  };
  OptionTagByTagId.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.tagId) | 0;
    return result;
  };
  OptionTagByTagId.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.tagId, other.tagId))));
  };
  function TagTagByPrimaryTagId(primaryTagId) {
    this.primaryTagId = primaryTagId;
  }
  TagTagByPrimaryTagId.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = this.primaryTagId) != null ? tmp$.equals(entity.primaryTagId) : null;
  };
  TagTagByPrimaryTagId.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagTagByPrimaryTagId',
    interfaces: [RepositoryCriteria]
  };
  TagTagByPrimaryTagId.prototype.component1 = function () {
    return this.primaryTagId;
  };
  TagTagByPrimaryTagId.prototype.copy_apv9qp$ = function (primaryTagId) {
    return new TagTagByPrimaryTagId(primaryTagId === void 0 ? this.primaryTagId : primaryTagId);
  };
  TagTagByPrimaryTagId.prototype.toString = function () {
    return 'TagTagByPrimaryTagId(primaryTagId=' + Kotlin.toString(this.primaryTagId) + ')';
  };
  TagTagByPrimaryTagId.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.primaryTagId) | 0;
    return result;
  };
  TagTagByPrimaryTagId.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.primaryTagId, other.primaryTagId))));
  };
  function TagTagByTagId(tagId) {
    this.tagId = tagId;
  }
  TagTagByTagId.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = this.tagId) != null ? tmp$.equals(entity.tagId) : null;
  };
  TagTagByTagId.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagTagByTagId',
    interfaces: [RepositoryCriteria]
  };
  TagTagByTagId.prototype.component1 = function () {
    return this.tagId;
  };
  TagTagByTagId.prototype.copy_apv9qp$ = function (tagId) {
    return new TagTagByTagId(tagId === void 0 ? this.tagId : tagId);
  };
  TagTagByTagId.prototype.toString = function () {
    return 'TagTagByTagId(tagId=' + Kotlin.toString(this.tagId) + ')';
  };
  TagTagByTagId.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.tagId) | 0;
    return result;
  };
  TagTagByTagId.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.tagId, other.tagId))));
  };
  function TagTagByTagValue(tagValue) {
    this.tagValue = tagValue;
  }
  TagTagByTagValue.prototype.invoke_g5e3t3$ = function (entity) {
    var tmp$;
    return (tmp$ = entity.tagValue) != null ? tmp$.equals(this.tagValue) : null;
  };
  TagTagByTagValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TagTagByTagValue',
    interfaces: [RepositoryCriteria]
  };
  TagTagByTagValue.prototype.component1 = function () {
    return this.tagValue;
  };
  TagTagByTagValue.prototype.copy_yc1cg6$ = function (tagValue) {
    return new TagTagByTagValue(tagValue === void 0 ? this.tagValue : tagValue);
  };
  TagTagByTagValue.prototype.toString = function () {
    return 'TagTagByTagValue(tagValue=' + Kotlin.toString(this.tagValue) + ')';
  };
  TagTagByTagValue.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.tagValue) | 0;
    return result;
  };
  TagTagByTagValue.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.tagValue, other.tagValue))));
  };
  function nonEmpty($receiver) {
    return $receiver.iterator().hasNext();
  }
  function isEmpty($receiver) {
    return !nonEmpty($receiver);
  }
  function max(item1, item2) {
    if (Kotlin.compareTo(item1, item2) >= 0) {
      return item1;
    }
     else {
      return item2;
    }
  }
  function PlatformProvider() {
    PlatformProvider$Companion_getInstance();
  }
  function PlatformProvider$Companion() {
    PlatformProvider$Companion_instance = this;
    this.instance_ivc7ry$_0 = properties.Delegates.notNull_30y1fr$();
  }
  Object.defineProperty(PlatformProvider$Companion.prototype, 'instance', {
    get: function () {
      return this.instance_ivc7ry$_0.getValue_lrcp0p$(this, new PropertyMetadata('instance'));
    },
    set: function (instance) {
      this.instance_ivc7ry$_0.setValue_9rddgb$(this, new PropertyMetadata('instance'), instance);
    }
  });
  PlatformProvider$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var PlatformProvider$Companion_instance = null;
  function PlatformProvider$Companion_getInstance() {
    if (PlatformProvider$Companion_instance === null) {
      new PlatformProvider$Companion();
    }
    return PlatformProvider$Companion_instance;
  }
  PlatformProvider.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'PlatformProvider',
    interfaces: []
  };
  function parseCurrency($receiver) {
    return PlatformProvider$Companion_getInstance().instance.parseCurrency_61zpoe$($receiver);
  }
  function formatCurrency($receiver) {
    return PlatformProvider$Companion_getInstance().instance.formatCurrency_14dthe$($receiver);
  }
  function formatCurrencyForInput($receiver) {
    return PlatformProvider$Companion_getInstance().instance.formatCurrencyForInput_14dthe$($receiver);
  }
  function ProviderDate() {
  }
  ProviderDate.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ProviderDate',
    interfaces: []
  };
  function ProviderDate_0(input) {
    return PlatformProvider$Companion_getInstance().instance.toDate_61zpoe$(input);
  }
  function RichDate(date) {
    return RichDate_0(date.year, date.month, date.dayOfMonth);
  }
  function toRichDate_0($receiver) {
    return RichDate($receiver);
  }
  function RichDate_0(year, month, day) {
    return new RichDate_3(((year - RichDate$Companion_getInstance().baseYear_8be2vx$ | 0) * 12 | 0) + month | 0, day);
  }
  function RichDate_1(millis) {
    return toRichDate_0(PlatformProvider$Companion_getInstance().instance.toDate_s8cxhz$(millis));
  }
  function RichDate_2(input) {
    var date = PlatformProvider$Companion_getInstance().instance.toDate_61zpoe$(input);
    return toRichDate_0(date);
  }
  function RichDate_3(months, days) {
    RichDate$Companion_getInstance();
    this.months = months;
    this.days = days;
    this.date = PlatformProvider$Companion_getInstance().instance.toDate_qt1dr2$((this.months / 12 | 0) + RichDate$Companion_getInstance().baseYear_8be2vx$ | 0, this.months % 12, this.days);
  }
  RichDate_3.prototype.compareTo_11rb$ = function (other) {
    return get_millis(this).compareTo_11rb$(get_millis(other));
  };
  RichDate_3.prototype.toString = function () {
    return this.date.toString();
  };
  function RichDate$Companion() {
    RichDate$Companion_instance = this;
    this.baseYear_8be2vx$ = 1970;
  }
  RichDate$Companion.prototype.today = function () {
    return RichDate(PlatformProvider$Companion_getInstance().instance.now());
  };
  RichDate$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var RichDate$Companion_instance = null;
  function RichDate$Companion_getInstance() {
    if (RichDate$Companion_instance === null) {
      new RichDate$Companion();
    }
    return RichDate$Companion_instance;
  }
  RichDate_3.prototype.after_kiaetp$ = function (other) {
    return this.compareTo_11rb$(other) > 0;
  };
  RichDate_3.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RichDate',
    interfaces: [Comparable]
  };
  RichDate_3.prototype.component1 = function () {
    return this.months;
  };
  RichDate_3.prototype.component2 = function () {
    return this.days;
  };
  RichDate_3.prototype.copy_vux9f0$ = function (months, days) {
    return new RichDate_3(months === void 0 ? this.months : months, days === void 0 ? this.days : days);
  };
  RichDate_3.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.months) | 0;
    result = result * 31 + Kotlin.hashCode(this.days) | 0;
    return result;
  };
  RichDate_3.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.months, other.months) && Kotlin.equals(this.days, other.days)))));
  };
  function get_millis($receiver) {
    return $receiver.date.millisecondsSinceUnixEpoch;
  }
  function get_dayOfMonth($receiver) {
    return $receiver.date.dayOfMonth;
  }
  function SwitchableRepository(delegate, undoProvider) {
    if (undoProvider === void 0)
      undoProvider = UndoProvider$Companion_getInstance().empty;
    this.undoProvider_1wr4vs$_0 = undoProvider;
    this._delegate_69urbx$_0 = delegate;
    this.listeners_w34pug$_0 = ArrayList_init(4);
  }
  function SwitchableRepository$set_SwitchableRepository$delegate$lambda(this$SwitchableRepository, closure$delegate) {
    return function () {
      var $receiver = this$SwitchableRepository.listeners_w34pug$_0;
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var this$SwitchableRepository_0 = this$SwitchableRepository;
        var closure$delegate_0 = closure$delegate;
        this$SwitchableRepository_0._delegate_69urbx$_0.removeListener_56i51t$(element);
        closure$delegate_0.addListener_56i51t$(element);
        var tmp$_0;
        tmp$_0 = this$SwitchableRepository_0._delegate_69urbx$_0.list().iterator();
        while (tmp$_0.hasNext()) {
          var element_0 = tmp$_0.next();
          element.onRemoved_11rb$(element_0);
        }
        var tmp$_1;
        tmp$_1 = closure$delegate_0.list().iterator();
        while (tmp$_1.hasNext()) {
          var element_1 = tmp$_1.next();
          element.onSaved_xwzc9q$(null, element_1);
        }
      }
      this$SwitchableRepository._delegate_69urbx$_0 = closure$delegate;
      return Unit;
    };
  }
  Object.defineProperty(SwitchableRepository.prototype, 'delegate', {
    get: function () {
      return this._delegate_69urbx$_0;
    },
    set: function (delegate) {
      if (!equals(delegate, this._delegate_69urbx$_0)) {
        this.undoProvider_1wr4vs$_0.notUndoable_klfg04$(SwitchableRepository$set_SwitchableRepository$delegate$lambda(this, delegate));
      }
    }
  });
  SwitchableRepository.prototype.generateID = function () {
    return this.delegate.generateID();
  };
  SwitchableRepository.prototype.find_83a66n$ = function (id) {
    return this.delegate.find_83a66n$(id);
  };
  SwitchableRepository.prototype.list = function () {
    return this.delegate.list();
  };
  SwitchableRepository.prototype.save_tf6uce$ = function (original, replacement) {
    return this.delegate.save_tf6uce$(original, replacement);
  };
  SwitchableRepository.prototype.remove_83a66n$ = function (id) {
    this.delegate.remove_83a66n$(id);
  };
  SwitchableRepository.prototype.addListener_56i51t$ = function (listener) {
    this.listeners_w34pug$_0.add_11rb$(listener);
    this.delegate.addListener_56i51t$(listener);
  };
  SwitchableRepository.prototype.removeListener_56i51t$ = function (listener) {
    this.delegate.removeListener_56i51t$(listener);
    this.listeners_w34pug$_0.remove_11rb$(listener);
  };
  Object.defineProperty(SwitchableRepository.prototype, 'localStorageKeys', {
    get: function () {
      return this.delegate.localStorageKeys;
    }
  });
  SwitchableRepository.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SwitchableRepository',
    interfaces: [Repository]
  };
  var currentContext;
  function inContext(contextName, f) {
    var contextBefore = 'before ' + contextName;
    currentContext = contextBefore;
    var result;
    try {
      result = f();
    }
    finally {
      var contextDuring = 'during ' + contextName;
      currentContext = !equals(currentContext, contextBefore) ? contextDuring + ', ' + currentContext : contextDuring;
    }
    currentContext = 'after ' + contextName;
    return result;
  }
  function ID(_id) {
    this._id = _id;
  }
  ID.prototype.equals = function (other) {
    return Kotlin.isType(other, ID) ? equals(this._id, other._id) : false;
  };
  ID.prototype.hashCode = function () {
    return hashCode(this._id);
  };
  ID.prototype.toString = function () {
    return this._id;
  };
  ID.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ID',
    interfaces: []
  };
  ID.prototype.component1 = function () {
    return this._id;
  };
  ID.prototype.copy_61zpoe$ = function (_id) {
    return new ID(_id === void 0 ? this._id : _id);
  };
  function ID_0(id) {
    return new ID(id.toString());
  }
  function WithID() {
  }
  WithID.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'WithID',
    interfaces: []
  };
  function IdGenerator() {
    this.next_0 = Kotlin.Long.ONE;
  }
  IdGenerator.prototype.generateID = function () {
    var result = this.next_0;
    this.next_0 = result.add(Kotlin.Long.fromInt(1));
    return result;
  };
  IdGenerator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IdGenerator',
    interfaces: []
  };
  function Repository() {
  }
  Repository.prototype.list_fmyu0x$ = function (criteria) {
    var $receiver = this.list();
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (criteria.invoke_g5e3t3$(element))
        destination.add_11rb$(element);
    }
    return destination;
  };
  Repository.prototype.save_g5e3t3$ = function (entity) {
    var tmp$;
    var original = (tmp$ = entity.getID()) != null ? this.find_83a66n$(tmp$) : null;
    return this.save_tf6uce$(original, entity);
  };
  Repository.prototype.saveAndGet_g5e3t3$ = function (entity) {
    return entity.withID_83a66n$(this.save_g5e3t3$(entity));
  };
  Repository.prototype.remove_83a66n$ = function (id) {
    var item = this.find_83a66n$(id);
    if (item != null) {
      this.remove_g5e3t3$(item);
    }
  };
  Repository.prototype.remove_g5e3t3$ = function (item) {
    var tmp$;
    if ((tmp$ = item.getID()) != null) {
      this.remove_83a66n$(tmp$);
    }
  };
  Repository.prototype.find_83a66n$ = function (id) {
    var $receiver = this.list();
    var firstOrNull$result;
    firstOrNull$break: do {
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (equals(element.getID(), id)) {
          firstOrNull$result = element;
          break firstOrNull$break;
        }
      }
      firstOrNull$result = null;
    }
     while (false);
    return firstOrNull$result;
  };
  Repository.prototype.withID_dy6xw6$$default = function (replacement, id) {
    if (replacement.getID() != null) {
      return replacement;
    }
     else {
      return replacement.withID_83a66n$(id);
    }
  };
  Repository.prototype.withID_dy6xw6$ = function (replacement, id, callback$default) {
    if (id === void 0)
      id = this.generateID();
    return callback$default ? callback$default(replacement, id) : this.withID_dy6xw6$$default(replacement, id);
  };
  Repository.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Repository',
    interfaces: []
  };
  function removeAll_0($receiver, criteria) {
    var tmp$;
    tmp$ = $receiver.list_fmyu0x$(criteria).iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      $receiver.remove_g5e3t3$(element);
    }
  }
  function EmptyRepository() {
    this.localStorageKeys_uxq7gq$_0 = emptySet();
  }
  EmptyRepository.prototype.list = function () {
    return emptyList();
  };
  EmptyRepository.prototype.save_tf6uce$ = function (original, replacement) {
    throw new UnsupportedOperationException('read-only');
  };
  EmptyRepository.prototype.remove_83a66n$ = function (id) {
  };
  EmptyRepository.prototype.generateID = function () {
    throw new UnsupportedOperationException('read-only');
  };
  EmptyRepository.prototype.addListener_56i51t$ = function (listener) {
  };
  EmptyRepository.prototype.removeListener_56i51t$ = function (listener) {
  };
  Object.defineProperty(EmptyRepository.prototype, 'localStorageKeys', {
    get: function () {
      return this.localStorageKeys_uxq7gq$_0;
    }
  });
  EmptyRepository.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EmptyRepository',
    interfaces: [Repository]
  };
  function RepositoryCriteria() {
  }
  RepositoryCriteria.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'RepositoryCriteria',
    interfaces: []
  };
  function AllItems() {
  }
  AllItems.prototype.invoke_g5e3t3$ = function (entity) {
    return true;
  };
  AllItems.prototype.equals = function (other) {
    return Kotlin.isType(other, AllItems);
  };
  AllItems.prototype.hashCode = function () {
    return 93993;
  };
  AllItems.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AllItems',
    interfaces: [RepositoryCriteria]
  };
  function allItems() {
    return new AllItems();
  }
  function RepositoryListener() {
  }
  RepositoryListener.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'RepositoryListener',
    interfaces: []
  };
  function getOrGenerateID($receiver, originalID, replacement) {
    var newID = originalID != null ? originalID : $receiver.generateID();
    var replacementWithID = $receiver.withID_dy6xw6$(replacement, newID);
    return replacementWithID;
  }
  function putIntoList(mutableList, replacementWithID, originalID) {
    var tmp$;
    if (originalID != null) {
      var indexOfFirst$result;
      indexOfFirst$break: do {
        var tmp$_0;
        var index = 0;
        tmp$_0 = mutableList.iterator();
        while (tmp$_0.hasNext()) {
          var item = tmp$_0.next();
          if (equals(item.getID(), originalID)) {
            indexOfFirst$result = index;
            break indexOfFirst$break;
          }
          index = index + 1 | 0;
        }
        indexOfFirst$result = -1;
      }
       while (false);
      tmp$ = indexOfFirst$result;
    }
     else
      tmp$ = -1;
    var index_0 = tmp$;
    if (index_0 >= 0) {
      mutableList.set_wxm5ur$(index_0, replacementWithID);
    }
     else {
      mutableList.add_11rb$(replacementWithID);
    }
  }
  function UndoProvider() {
    UndoProvider$Companion_getInstance();
  }
  UndoProvider.prototype.undoableSave_8wrb8g$ = function (original, replacementWithID, function_0) {
    var isUpdate = original != null && original.getID() != null;
    var pastTenseDescription = isUpdate ? 'Updated ' + toString(original) : 'Added ' + replacementWithID;
    var undoPastTenseDescription = isUpdate ? 'Reverted ' + toString(original) : 'Deleted ' + replacementWithID;
    return this.undoable_u8di54$(pastTenseDescription, undoPastTenseDescription, function_0);
  };
  function UndoProvider$Companion() {
    UndoProvider$Companion_instance = this;
    this.empty = new UndoProvider$Companion$empty$ObjectLiteral();
  }
  function UndoProvider$Companion$empty$ObjectLiteral() {
  }
  UndoProvider$Companion$empty$ObjectLiteral.prototype.undoable_u8di54$ = function (pastTenseDescription, undoPastTenseDescription, function_0) {
    return function_0();
  };
  UndoProvider$Companion$empty$ObjectLiteral.prototype.notUndoable_klfg04$ = function (function_0) {
    return function_0();
  };
  UndoProvider$Companion$empty$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [UndoProvider]
  };
  UndoProvider$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var UndoProvider$Companion_instance = null;
  function UndoProvider$Companion_getInstance() {
    if (UndoProvider$Companion_instance === null) {
      new UndoProvider$Companion();
    }
    return UndoProvider$Companion_instance;
  }
  UndoProvider.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'UndoProvider',
    interfaces: []
  };
  function CompositeRepository(repositoryMap, undoProvider, categorizer) {
    if (undoProvider === void 0)
      undoProvider = UndoProvider$Companion_getInstance().empty;
    this.repositoryMap_yyi15e$_0 = repositoryMap;
    this.undoProvider_cycngh$_0 = undoProvider;
    this.categorizer_90yszd$_0 = categorizer;
  }
  CompositeRepository.prototype.list = function () {
    var $receiver = this.repositoryMap_yyi15e$_0.values;
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var list = element.list();
      addAll(destination, list);
    }
    return destination;
  };
  function CompositeRepository$find$lambda(closure$id) {
    return function (it) {
      return it.find_83a66n$(closure$id);
    };
  }
  CompositeRepository.prototype.find_83a66n$ = function (id) {
    var $receiver = map_0(asSequence(this.repositoryMap_yyi15e$_0.values), CompositeRepository$find$lambda(id));
    var firstOrNull$result;
    firstOrNull$break: do {
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (element != null) {
          firstOrNull$result = element;
          break firstOrNull$break;
        }
      }
      firstOrNull$result = null;
    }
     while (false);
    return firstOrNull$result;
  };
  function CompositeRepository$save$lambda(closure$originalCategory, this$CompositeRepository, closure$category, closure$original, closure$replacementRepository, closure$replacement) {
    return function () {
      var tmp$;
      if (closure$originalCategory != null) {
        var $receiver = closure$originalCategory;
        var this$CompositeRepository_0 = this$CompositeRepository;
        var closure$category_0 = closure$category;
        var tmp$_0;
        var tmp$_1;
        if ((tmp$_0 = this$CompositeRepository_0.repositoryMap_yyi15e$_0.get_11rb$($receiver)) != null)
          tmp$_1 = tmp$_0;
        else {
          throw new IllegalStateException_init(('no repository found for category=' + closure$category_0).toString());
        }
        tmp$ = tmp$_1;
      }
       else
        tmp$ = null;
      var originalRepository = tmp$;
      originalRepository != null ? (originalRepository.remove_g5e3t3$(closure$original), Unit) : null;
      return closure$replacementRepository.save_tf6uce$(null, closure$replacement);
    };
  }
  CompositeRepository.prototype.save_tf6uce$ = function (original, replacement) {
    var tmp$;
    var originalCategory = original != null ? this.categorizer_90yszd$_0(original) : null;
    var category = this.categorizer_90yszd$_0(replacement);
    var tmp$_0;
    if ((tmp$ = this.repositoryMap_yyi15e$_0.get_11rb$(category)) != null)
      tmp$_0 = tmp$;
    else {
      throw new IllegalStateException_init(('no repository found for category=' + category).toString());
    }
    var replacementRepository = tmp$_0;
    if (equals(originalCategory, category)) {
      return replacementRepository.save_tf6uce$(original, replacement);
    }
     else {
      return this.undoProvider_cycngh$_0.undoableSave_8wrb8g$(original, replacement, CompositeRepository$save$lambda(originalCategory, this, category, original, replacementRepository, replacement));
    }
  };
  CompositeRepository.prototype.remove_83a66n$ = function (id) {
    var forEach$result;
    var tmp$;
    tmp$ = asSequence(this.repositoryMap_yyi15e$_0.values).iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      element.remove_83a66n$(id);
    }
    return forEach$result;
  };
  CompositeRepository.prototype.generateID = function () {
    return ensureNotNull(firstOrNull(this.repositoryMap_yyi15e$_0.values)).generateID();
  };
  CompositeRepository.prototype.addListener_56i51t$ = function (listener) {
    var tmp$;
    tmp$ = this.repositoryMap_yyi15e$_0.values.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      element.addListener_56i51t$(listener);
    }
  };
  CompositeRepository.prototype.removeListener_56i51t$ = function (listener) {
    var tmp$;
    tmp$ = this.repositoryMap_yyi15e$_0.values.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      element.removeListener_56i51t$(listener);
    }
  };
  Object.defineProperty(CompositeRepository.prototype, 'localStorageKeys', {
    get: function () {
      var $receiver = this.repositoryMap_yyi15e$_0.values;
      var destination = ArrayList_init();
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var list = element.localStorageKeys;
        addAll(destination, list);
      }
      return toSet_0(destination);
    }
  });
  CompositeRepository.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CompositeRepository',
    interfaces: [Repository]
  };
  function InMemoryRepository() {
    InMemoryRepository$Companion_getInstance();
    this.list_1rxyxz$_0 = ArrayList_init();
    this.listeners_9dxw4w$_0 = ArrayList_init(4);
    this.localStorageKeys_8hjhi9$_0 = emptySet();
  }
  InMemoryRepository.prototype.list = function () {
    return toList(this.list_1rxyxz$_0);
  };
  InMemoryRepository.prototype.save_tf6uce$ = function (original, replacement) {
    var tmp$;
    var originalID = (tmp$ = original != null ? original.getID() : null) != null ? tmp$ : replacement.getID();
    var replacementWithID = getOrGenerateID(this, originalID, replacement);
    var newID = ensureNotNull(replacementWithID.getID());
    var originalWithID = originalID != null ? original != null ? original.withID_83a66n$(originalID) : null : null;
    if (!equals(originalWithID, replacementWithID)) {
      putIntoList(this.list_1rxyxz$_0, replacementWithID, originalID);
      var tmp$_0;
      tmp$_0 = this.listeners_9dxw4w$_0.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        element.onSaved_xwzc9q$(originalWithID, replacementWithID);
      }
    }
    return newID;
  };
  InMemoryRepository.prototype.generateID = function () {
    return new ID(InMemoryRepository$Companion_getInstance().idGenerator_0.generateID().toString());
  };
  InMemoryRepository.prototype.remove_83a66n$ = function (id) {
    var $receiver = this.list_1rxyxz$_0;
    var indexOfFirst$result;
    indexOfFirst$break: do {
      var tmp$;
      var index = 0;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        if (equals(item.getID(), id)) {
          indexOfFirst$result = index;
          break indexOfFirst$break;
        }
        index = index + 1 | 0;
      }
      indexOfFirst$result = -1;
    }
     while (false);
    var index_0 = indexOfFirst$result;
    if (index_0 >= 0) {
      var item_0 = this.list_1rxyxz$_0.removeAt_za3lpa$(index_0);
      var tmp$_0;
      tmp$_0 = this.listeners_9dxw4w$_0.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        element.onRemoved_11rb$(item_0);
      }
    }
  };
  InMemoryRepository.prototype.addListener_56i51t$ = function (listener) {
    this.listeners_9dxw4w$_0.add_11rb$(listener);
  };
  InMemoryRepository.prototype.removeListener_56i51t$ = function (listener) {
    this.listeners_9dxw4w$_0.remove_11rb$(listener);
  };
  Object.defineProperty(InMemoryRepository.prototype, 'localStorageKeys', {
    get: function () {
      return this.localStorageKeys_8hjhi9$_0;
    }
  });
  function InMemoryRepository$Companion() {
    InMemoryRepository$Companion_instance = this;
    this.idGenerator_0 = new IdGenerator();
  }
  InMemoryRepository$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var InMemoryRepository$Companion_instance = null;
  function InMemoryRepository$Companion_getInstance() {
    if (InMemoryRepository$Companion_instance === null) {
      new InMemoryRepository$Companion();
    }
    return InMemoryRepository$Companion_instance;
  }
  InMemoryRepository.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'InMemoryRepository',
    interfaces: [Repository]
  };
  function FieldSelector() {
  }
  FieldSelector.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FieldSelector',
    interfaces: []
  };
  function IdFieldSelector() {
  }
  IdFieldSelector.prototype.invoke_g5e3t3$ = function (entity) {
    return ensureNotNull(entity.getID());
  };
  IdFieldSelector.prototype.equals = function (other) {
    return Kotlin.isType(other, IdFieldSelector);
  };
  IdFieldSelector.prototype.hashCode = function () {
    return hashCode(this.toString());
  };
  IdFieldSelector.prototype.toString = function () {
    return 'IdFieldSelector';
  };
  IdFieldSelector.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IdFieldSelector',
    interfaces: [FieldSelector]
  };
  function SelfSelector() {
  }
  SelfSelector.prototype.invoke_g5e3t3$ = function (entity) {
    return entity;
  };
  SelfSelector.prototype.equals = function (other) {
    return Kotlin.isType(other, SelfSelector);
  };
  SelfSelector.prototype.hashCode = function () {
    return hashCode(this.toString());
  };
  SelfSelector.prototype.toString = function () {
    return 'SelfSelector';
  };
  SelfSelector.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SelfSelector',
    interfaces: [FieldSelector]
  };
  var package$client = _.client || (_.client = {});
  package$client.buttonBar_kramet$ = buttonBar;
  Object.defineProperty(DataSetModel, 'Companion', {
    get: DataSetModel$Companion_getInstance
  });
  package$client.DataSetModel = DataSetModel;
  package$client.dataSetScreen_20gkd0$ = dataSetScreen;
  Object.defineProperty(DataSetsModel, 'Companion', {
    get: DataSetsModel$Companion_getInstance
  });
  package$client.DataSetsModel = DataSetsModel;
  package$client.dataSetsScreen_1mogkk$ = dataSetsScreen;
  Object.defineProperty(OptionModel, 'Companion', {
    get: OptionModel$Companion_getInstance
  });
  package$client.OptionModel_init_qxzinw$ = OptionModel_init;
  package$client.OptionModel = OptionModel;
  package$client.optionDialog_c6e427$ = optionDialog;
  package$client.optionEntryScreen_c6e427$ = optionEntryScreen;
  package$client.OptionTagModel = OptionTagModel;
  package$client.buttonForOptionTag_vgu8n4$ = buttonForOptionTag;
  package$client.optionTagDialog_ten57d$ = optionTagDialog;
  package$client.optionTagTabContent_q1kmx9$ = optionTagTabContent;
  package$client.tagCriteriaTabsContent_516umt$ = tagCriteriaTabsContent;
  package$client.tagTabContent_bp305w$ = tagTabContent;
  Object.defineProperty(OptionsModel, 'Companion', {
    get: OptionsModel$Companion_getInstance
  });
  package$client.OptionsModel = OptionsModel;
  package$client.TagIdGroups = TagIdGroups;
  package$client.OptionRow = OptionRow;
  package$client.optionsScreen_nzjrwn$ = optionsScreen;
  package$client.TagCriteriaModel = TagCriteriaModel;
  package$client.buttonForTagCriteria_pkw637$ = buttonForTagCriteria;
  package$client.tagCriteriaDialog_sgs70t$ = tagCriteriaDialog;
  package$client.buttonToSetTagCriteriaValue_o3c004$ = buttonToSetTagCriteriaValue;
  package$client.TagModel = TagModel;
  package$client.tagEditForm_ldo21$ = tagEditForm;
  package$client.TagTagModel = TagTagModel;
  package$client.buttonForTagTag_t4cqtz$ = buttonForTagTag;
  package$client.tagTagDialog_uqoazq$ = tagTagDialog;
  package$client.TagValueForOptionLabelModel = TagValueForOptionLabelModel;
  package$client.getPercentage_p4zxyl$ = getPercentage;
  package$client.setPercentage_ydbqtl$ = setPercentage;
  package$client.tagValueForOptionLabelDialog_ortdw9$ = tagValueForOptionLabelDialog;
  package$client.TagValueModel = TagValueModel;
  package$client.buttonForTagValue_353kc7$ = buttonForTagValue;
  package$client.buttonToSetTagValue_vq9b48$ = buttonToSetTagValue;
  var package$component = package$client.component || (package$client.component = {});
  Object.defineProperty(package$component, 'FileBackupComponent', {
    get: FileBackupComponent_getInstance
  });
  package$component.flaticon_9bm2zh$ = flaticon;
  package$component.get_visible_y4uc6z$ = get_visible;
  package$component.set_visible_rbb9xy$ = set_visible;
  package$component.get_responsiveWidth_y4uc6z$ = get_responsiveWidth;
  package$component.set_responsiveWidth_kn32kx$ = set_responsiveWidth;
  Object.defineProperty(package$component, 'UndoComponent', {
    get: UndoComponent_getInstance
  });
  package$component.undoComponent_y4uc6z$ = undoComponent;
  package$component.Command = Command;
  var package$cordova = package$client.cordova || (package$client.cordova = {});
  package$cordova.requestFileSystem_b1yhyb$ = requestFileSystem;
  package$cordova.resolveLocalFileSystemURL_35y70p$ = resolveLocalFileSystemURL;
  package$cordova.resolveLocalFileSystemURI_35y70p$ = resolveLocalFileSystemURI;
  package$cordova.get_PERSISTENT_nz12v2$ = get_PERSISTENT;
  package$cordova.get_TEMPORARY_nz12v2$ = get_TEMPORARY;
  package$cordova.get_file_3b1kfs$ = get_file;
  package$cordova.get_cordova_nz12v2$ = get_cordova;
  package$cordova.get_StatusBar_nz12v2$ = get_StatusBar;
  package$cordova.initializeForCordova = initializeForCordova;
  var package$ext = package$client.ext || (package$client.ext = {});
  var package$firebase = package$ext.firebase || (package$ext.firebase = {});
  package$firebase.FirebaseRepositorySync = FirebaseRepositorySync;
  package$firebase.FirebaseAndLocalRepository_39eprd$ = FirebaseAndLocalRepository;
  Object.defineProperty(ProtectionLevel, 'PRIVATE', {
    get: ProtectionLevel$PRIVATE_getInstance
  });
  Object.defineProperty(ProtectionLevel, 'PUBLIC', {
    get: ProtectionLevel$PUBLIC_getInstance
  });
  Object.defineProperty(ProtectionLevel, 'OWNED', {
    get: ProtectionLevel$OWNED_getInstance
  });
  package$firebase.ProtectionLevel = ProtectionLevel;
  package$firebase.PublicWithChangeLogAndPrivateFirebaseRepository_wtm93s$ = PublicWithChangeLogAndPrivateFirebaseRepository;
  package$firebase.PrivateFirebaseRepository_kiatcu$ = PrivateFirebaseRepository;
  package$firebase.get_keys_t1yf75$ = get_keys;
  package$firebase.RepositoryWithFirebaseChangeLog = RepositoryWithFirebaseChangeLog;
  package$firebase.ChangeLogEntry = ChangeLogEntry;
  package$client.toNormal_do6vjj$ = toNormal;
  package$client.toNormal_eer8kx$ = toNormal_0;
  package$client.toNormal_vp28ku$ = toNormal_1;
  package$client.toNormal_j8xn6k$ = toNormal_2;
  package$client.toNormal_u9ga1v$ = toNormal_3;
  Object.defineProperty(package$client, 'appName', {
    get: function () {
      return appName;
    }
  });
  Object.defineProperty(package$client, 'appFilenamePrefix', {
    get: function () {
      return appFilenamePrefix;
    }
  });
  Object.defineProperty(package$client, 'page', {
    get: function () {
      return page;
    }
  });
  package$client.setChildWithoutSplash_3kb0dw$ = setChildWithoutSplash;
  Object.defineProperty(package$client, 'UI', {
    get: UI_getInstance
  });
  package$client.main_kand9s$ = main;
  Object.defineProperty(package$client, 'Factory', {
    get: Factory_getInstance
  });
  package$client.get_protectionLevel_8156ze$ = get_protectionLevel;
  package$client.alphabeticalTagIds_mgcebi$ = alphabeticalTagIds;
  package$client.findPercentageProperty_568bv1$ = findPercentageProperty;
  package$client.findPercentageProperty_i46y6l$ = findPercentageProperty_0;
  var package$util = package$client.util || (package$client.util = {});
  Object.defineProperty(package$util, 'JavascriptProvider', {
    get: JavascriptProvider_getInstance
  });
  package$util.LocalStorageRepository = LocalStorageRepository;
  package$util.MomentDate = MomentDate;
  package$util.toRichDate_y2c99b$ = toRichDate;
  package$util.RepositoryCache = RepositoryCache;
  package$util.repositoryCache_tko4xr$ = repositoryCache;
  package$util.findProperty_1cdbts$ = findProperty;
  package$util.idListProperty_27s5da$ = idListProperty;
  package$util.RepositoryQuery = RepositoryQuery;
  package$util.list_cfdrao$ = list;
  package$util.listProperty_27s5da$ = listProperty;
  package$util.listProperty_cfdrao$ = listProperty_0;
  package$util.findFirstOrNullProperty_27s5da$ = findFirstOrNullProperty;
  package$util.handleError_tcv7n7$ = handleError;
  package$util.handlingErrors_48uw2f$ = handlingErrors;
  package$util.toNormal_dc9af6$ = toNormal_4;
  package$util.toID_qsehtd$ = toID;
  package$util.toMoment_3j1q8s$ = toMoment;
  package$util.toNormal_ws8rvx$ = toNormal_5;
  package$util.toNormal_ybrm1s$ = toNormal_6;
  package$util.toNormalNotNull_rdkahx$ = toNormalNotNull;
  package$util.orElse_qhh9tb$ = orElse;
  package$util.mapEachReusing_eo2pwk$ = mapEachReusing;
  package$util.mapEachReusingByID_gp1gva$ = mapEachReusingByID;
  package$util.mapWithCacheByID_ecb1c$ = mapWithCacheByID;
  package$util.toggleItem_yhawqa$ = toggleItem;
  package$util.contains_jujcbb$ = contains_1;
  package$util.contains_ewir64$ = contains_2;
  package$util.emptyToNull_5cw0du$ = emptyToNull;
  var package$common = _.common || (_.common = {});
  Object.defineProperty(package$common, 'Hello', {
    get: Hello_getInstance
  });
  package$common.DataSet = DataSet;
  package$common.Tag = Tag;
  package$common.Option = Option;
  package$common.truncate_6ic1pp$ = truncate;
  package$common.get_opposite_mz3mee$ = get_opposite;
  package$common.toPercentage_1v8dcc$ = toPercentage;
  package$common.OptionTag = OptionTag;
  package$common.OptionTagIdPair = OptionTagIdPair;
  package$common.TagValue = TagValue;
  package$common.OptionTagByOptionTagIdPair = OptionTagByOptionTagIdPair;
  package$common.TagTag = TagTag;
  package$common.TagTagIdPair = TagTagIdPair;
  package$common.TagTagByTagTagIdPair = TagTagByTagTagIdPair;
  Object.defineProperty(MatchScore, 'Companion', {
    get: MatchScore$Companion_getInstance
  });
  package$common.MatchScore = MatchScore;
  package$common.allOf_6sv122$ = allOf;
  package$common.mostOpinionated_6sv122$ = mostOpinionated;
  package$common.TagValueCriteria = TagValueCriteria;
  Object.defineProperty(TagCriteria, 'Companion', {
    get: TagCriteria$Companion_getInstance
  });
  package$common.TagCriteria = TagCriteria;
  package$common.DisallowedTagCriteria_shiqq7$ = DisallowedTagCriteria;
  package$common.min_dleff0$ = min;
  package$common.DisallowedTagCriteria = DisallowedTagCriteria_0;
  package$common.anyOf_e4s7na$ = anyOf;
  package$common.AnyOfTagCriteria = AnyOfTagCriteria;
  package$common.abs_mx4ult$ = abs_0;
  package$common.abs_za3lpa$ = abs_1;
  package$common.matchScore_mpbji8$ = matchScore;
  package$common.dataSet_scv40v$ = dataSet;
  package$common.DataSetContext = DataSetContext;
  package$common.OptionContext = OptionContext;
  package$common.setPercentage_cz3tqa$ = setPercentage_0;
  package$common.remove_568bv1$ = remove;
  package$common.setValue_f2z8wo$ = setValue;
  package$common.remove_i46y6l$ = remove_0;
  package$common.ModelStorage = ModelStorage;
  package$common.findOrCreate_t8ewq8$ = findOrCreate;
  package$common.getImpliedTagValues_491ctr$ = getImpliedTagValues;
  package$common.getTagValuesThatImplyTagValue_491ctr$ = getTagValuesThatImplyTagValue;
  package$common.getEffectiveTagCriteria_klqu5b$ = getEffectiveTagCriteria;
  package$common.OptionByDataSetId = OptionByDataSetId;
  package$common.TagByDataSetId = TagByDataSetId;
  package$common.TagByLabel = TagByLabel;
  package$common.OptionTagByDataSetId = OptionTagByDataSetId;
  Object.defineProperty(package$common, 'OptionTagIdPairField', {
    get: OptionTagIdPairField_getInstance
  });
  Object.defineProperty(package$common, 'TagTagIdPairField', {
    get: TagTagIdPairField_getInstance
  });
  Object.defineProperty(package$common, 'OptionTagValueField', {
    get: OptionTagValueField_getInstance
  });
  Object.defineProperty(package$common, 'TagValueField', {
    get: TagValueField_getInstance
  });
  package$common.OptionTagByOptionId = OptionTagByOptionId;
  package$common.OptionTagByTagId = OptionTagByTagId;
  package$common.TagTagByPrimaryTagId = TagTagByPrimaryTagId;
  package$common.TagTagByTagId = TagTagByTagId;
  package$common.TagTagByTagValue = TagTagByTagValue;
  var package$util_0 = package$common.util || (package$common.util = {});
  package$util_0.nonEmpty_7wnvza$ = nonEmpty;
  package$util_0.isEmpty_7wnvza$ = isEmpty;
  package$util_0.max_sdesaw$ = max;
  Object.defineProperty(PlatformProvider, 'Companion', {
    get: PlatformProvider$Companion_getInstance
  });
  package$util_0.PlatformProvider = PlatformProvider;
  package$util_0.parseCurrency_pdl1vz$ = parseCurrency;
  package$util_0.formatCurrency_yrwdxr$ = formatCurrency;
  package$util_0.formatCurrencyForInput_yrwdxr$ = formatCurrencyForInput;
  package$util_0.ProviderDate = ProviderDate;
  package$util_0.ProviderDate_61zpoe$ = ProviderDate_0;
  package$util_0.RichDate_t8qglk$ = RichDate;
  package$util_0.toRichDate_gvof2x$ = toRichDate_0;
  package$util_0.RichDate_qt1dr2$ = RichDate_0;
  package$util_0.RichDate_s8cxhz$ = RichDate_1;
  package$util_0.RichDate_61zpoe$ = RichDate_2;
  Object.defineProperty(RichDate_3, 'Companion', {
    get: RichDate$Companion_getInstance
  });
  package$util_0.RichDate = RichDate_3;
  package$util_0.get_millis_3j1q8s$ = get_millis;
  package$util_0.get_dayOfMonth_3j1q8s$ = get_dayOfMonth;
  package$util_0.SwitchableRepository = SwitchableRepository;
  Object.defineProperty(package$util_0, 'currentContext', {
    get: function () {
      return currentContext;
    },
    set: function (value) {
      currentContext = value;
    }
  });
  package$util_0.inContext_85cpgq$ = inContext;
  package$util_0.ID = ID;
  package$util_0.ID_qx7hx5$ = ID_0;
  package$util_0.WithID = WithID;
  package$util_0.IdGenerator = IdGenerator;
  package$util_0.Repository = Repository;
  package$util_0.removeAll_27s5da$ = removeAll_0;
  package$util_0.EmptyRepository = EmptyRepository;
  package$util_0.RepositoryCriteria = RepositoryCriteria;
  package$util_0.allItems_71g9mm$ = allItems;
  package$util_0.RepositoryListener = RepositoryListener;
  package$util_0.getOrGenerateID_ay2n0u$ = getOrGenerateID;
  package$util_0.putIntoList_55dqt5$ = putIntoList;
  Object.defineProperty(UndoProvider, 'Companion', {
    get: UndoProvider$Companion_getInstance
  });
  package$util_0.UndoProvider = UndoProvider;
  package$util_0.CompositeRepository = CompositeRepository;
  Object.defineProperty(InMemoryRepository, 'Companion', {
    get: InMemoryRepository$Companion_getInstance
  });
  package$util_0.InMemoryRepository = InMemoryRepository;
  package$util_0.FieldSelector = FieldSelector;
  package$util_0.IdFieldSelector = IdFieldSelector;
  package$util_0.SelfSelector = SelfSelector;
  UndoComponent.prototype.undoableSave_8wrb8g$ = UndoProvider.prototype.undoableSave_8wrb8g$;
  FirebaseRepositorySync.prototype.list_fmyu0x$ = Repository.prototype.list_fmyu0x$;
  FirebaseRepositorySync.prototype.save_g5e3t3$ = Repository.prototype.save_g5e3t3$;
  FirebaseRepositorySync.prototype.remove_g5e3t3$ = Repository.prototype.remove_g5e3t3$;
  FirebaseRepositorySync.prototype.find_83a66n$ = Repository.prototype.find_83a66n$;
  FirebaseRepositorySync.prototype.withID_dy6xw6$ = Repository.prototype.withID_dy6xw6$;
  FirebaseRepositorySync.prototype.withID_dy6xw6$$default = Repository.prototype.withID_dy6xw6$$default;
  FirebaseRepositorySync.prototype.saveAndGet_g5e3t3$ = Repository.prototype.saveAndGet_g5e3t3$;
  RepositoryWithFirebaseChangeLog.prototype.list_fmyu0x$ = Repository.prototype.list_fmyu0x$;
  RepositoryWithFirebaseChangeLog.prototype.save_g5e3t3$ = Repository.prototype.save_g5e3t3$;
  RepositoryWithFirebaseChangeLog.prototype.remove_83a66n$ = Repository.prototype.remove_83a66n$;
  RepositoryWithFirebaseChangeLog.prototype.find_83a66n$ = Repository.prototype.find_83a66n$;
  RepositoryWithFirebaseChangeLog.prototype.withID_dy6xw6$ = Repository.prototype.withID_dy6xw6$;
  RepositoryWithFirebaseChangeLog.prototype.withID_dy6xw6$$default = Repository.prototype.withID_dy6xw6$$default;
  RepositoryWithFirebaseChangeLog.prototype.saveAndGet_g5e3t3$ = Repository.prototype.saveAndGet_g5e3t3$;
  LocalStorageRepository.prototype.list_fmyu0x$ = Repository.prototype.list_fmyu0x$;
  LocalStorageRepository.prototype.save_g5e3t3$ = Repository.prototype.save_g5e3t3$;
  LocalStorageRepository.prototype.remove_g5e3t3$ = Repository.prototype.remove_g5e3t3$;
  LocalStorageRepository.prototype.saveAndGet_g5e3t3$ = Repository.prototype.saveAndGet_g5e3t3$;
  LocalStorageRepository.prototype.find_83a66n$ = Repository.prototype.find_83a66n$;
  LocalStorageRepository.prototype.withID_dy6xw6$ = Repository.prototype.withID_dy6xw6$;
  LocalStorageRepository.prototype.withID_dy6xw6$$default = Repository.prototype.withID_dy6xw6$$default;
  Object.defineProperty(TagCriteria.prototype, 'pivotalTagId', Object.getOwnPropertyDescriptor(TagValueCriteria.prototype, 'pivotalTagId'));
  TagCriteria.prototype.findDecidingKeyTagValue_onfp55$ = TagValueCriteria.prototype.findDecidingKeyTagValue_onfp55$;
  Object.defineProperty(DisallowedTagCriteria_0.prototype, 'pivotalTagId', Object.getOwnPropertyDescriptor(TagValueCriteria.prototype, 'pivotalTagId'));
  DisallowedTagCriteria_0.prototype.findDecidingKeyTagValue_onfp55$ = TagValueCriteria.prototype.findDecidingKeyTagValue_onfp55$;
  Object.defineProperty(AnyOfTagCriteria.prototype, 'pivotalTagId', Object.getOwnPropertyDescriptor(TagValueCriteria.prototype, 'pivotalTagId'));
  AnyOfTagCriteria.prototype.findDecidingKeyTagValue_onfp55$ = TagValueCriteria.prototype.findDecidingKeyTagValue_onfp55$;
  SwitchableRepository.prototype.list_fmyu0x$ = Repository.prototype.list_fmyu0x$;
  SwitchableRepository.prototype.save_g5e3t3$ = Repository.prototype.save_g5e3t3$;
  SwitchableRepository.prototype.remove_g5e3t3$ = Repository.prototype.remove_g5e3t3$;
  SwitchableRepository.prototype.withID_dy6xw6$ = Repository.prototype.withID_dy6xw6$;
  SwitchableRepository.prototype.withID_dy6xw6$$default = Repository.prototype.withID_dy6xw6$$default;
  SwitchableRepository.prototype.saveAndGet_g5e3t3$ = Repository.prototype.saveAndGet_g5e3t3$;
  EmptyRepository.prototype.list_fmyu0x$ = Repository.prototype.list_fmyu0x$;
  EmptyRepository.prototype.save_g5e3t3$ = Repository.prototype.save_g5e3t3$;
  EmptyRepository.prototype.remove_g5e3t3$ = Repository.prototype.remove_g5e3t3$;
  EmptyRepository.prototype.saveAndGet_g5e3t3$ = Repository.prototype.saveAndGet_g5e3t3$;
  EmptyRepository.prototype.find_83a66n$ = Repository.prototype.find_83a66n$;
  EmptyRepository.prototype.withID_dy6xw6$ = Repository.prototype.withID_dy6xw6$;
  EmptyRepository.prototype.withID_dy6xw6$$default = Repository.prototype.withID_dy6xw6$$default;
  UndoProvider$Companion$empty$ObjectLiteral.prototype.undoableSave_8wrb8g$ = UndoProvider.prototype.undoableSave_8wrb8g$;
  CompositeRepository.prototype.list_fmyu0x$ = Repository.prototype.list_fmyu0x$;
  CompositeRepository.prototype.save_g5e3t3$ = Repository.prototype.save_g5e3t3$;
  CompositeRepository.prototype.remove_g5e3t3$ = Repository.prototype.remove_g5e3t3$;
  CompositeRepository.prototype.saveAndGet_g5e3t3$ = Repository.prototype.saveAndGet_g5e3t3$;
  CompositeRepository.prototype.withID_dy6xw6$ = Repository.prototype.withID_dy6xw6$;
  CompositeRepository.prototype.withID_dy6xw6$$default = Repository.prototype.withID_dy6xw6$$default;
  InMemoryRepository.prototype.list_fmyu0x$ = Repository.prototype.list_fmyu0x$;
  InMemoryRepository.prototype.save_g5e3t3$ = Repository.prototype.save_g5e3t3$;
  InMemoryRepository.prototype.remove_g5e3t3$ = Repository.prototype.remove_g5e3t3$;
  InMemoryRepository.prototype.withID_dy6xw6$ = Repository.prototype.withID_dy6xw6$;
  InMemoryRepository.prototype.withID_dy6xw6$$default = Repository.prototype.withID_dy6xw6$$default;
  InMemoryRepository.prototype.saveAndGet_g5e3t3$ = Repository.prototype.saveAndGet_g5e3t3$;
  InMemoryRepository.prototype.find_83a66n$ = Repository.prototype.find_83a66n$;
  appName = 'Applicable Options';
  var $receiver = appName;
  appFilenamePrefix = Regex('\\W').replace_x2uqeu$($receiver, '').toLowerCase();
  var tmp$;
  page = Kotlin.isType(tmp$ = ensureNotNull(document.getElementById('page')), HTMLDivElement) ? tmp$ : throwCCE();
  repositoryCaches = LinkedHashMap_init();
  HIGH_AMOUNT = new Kotlin.Long(0, 1);
  currentContext = 'start-up';
  main([]);
  Kotlin.defineModule('webclient', _);
  return _;
}(typeof webclient === 'undefined' ? {} : webclient, kotlin, Yested, firebase);

//# sourceMappingURL=webclient.js.map
