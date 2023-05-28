package com.jss.notifier.domain

enum CainiaoStatus(val rawName: String):
  case PU_PICKUP_SUCCESS extends CainiaoStatus("PU_PICKUP_SUCCESS")
  case SC_INBOUND_SUCCESS extends CainiaoStatus("SC_INBOUND_SUCCESS")
  case SC_OUTBOUND_SUCCESS extends CainiaoStatus("SC_OUTBOUND_SUCCESS")
  case TD_TRANS_ARRIVE extends CainiaoStatus("TD_TRANS_ARRIVE")
  case LH_POST_COLLECTION extends CainiaoStatus("LH_POST_COLLECTION")
  case GTMS_SC_DEPART extends CainiaoStatus("GTMS_SC_DEPART")
  case TD_TRANS_DEPART extends CainiaoStatus("TD_TRANS_DEPART")
  case LH_HO_IN_SUCCESS extends CainiaoStatus("LH_HO_IN_SUCCESS")
  case LH_HO_AIRLINE extends CainiaoStatus("LH_HO_AIRLINE")
  case LH_DEPART extends CainiaoStatus("LH_DEPART")
  case LH_ARRIVE extends CainiaoStatus("LH_ARRIVE")
  case GTMS_ACCEPT extends CainiaoStatus("GTMS_ACCEPT")
  case CC_IM_SUCCESS extends CainiaoStatus("CC_IM_SUCCESS")
  case AE_GROUP_CLEARING_CUSTOMS extends CainiaoStatus("AE_GROUP_CLEARING_CUSTOMS")
  case GTMS_DO_DEPART extends CainiaoStatus("GTMS_DO_DEPART")
  case GTMS_SIGNED extends CainiaoStatus("GTMS_SIGNED")
