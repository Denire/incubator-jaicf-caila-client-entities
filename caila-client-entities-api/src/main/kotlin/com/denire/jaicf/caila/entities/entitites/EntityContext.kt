package com.denire.jaicf.caila.entities.entitites

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.context.ActionContext
import com.justai.jaicf.context.ActivatorContext
import com.justai.jaicf.reactions.Reactions

typealias EntityContext = ActionContext<out ActivatorContext, out BotRequest, out Reactions>