function fn_global_null(str)
{
  if(typeof str == "undefined" || str == null) {
     return '';
  } else {
    return str;
  }
}