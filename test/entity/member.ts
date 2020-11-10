
import {User} from './user'
import {FuncCompany} from './funccompany'
import {Agent} from './agent'
import { Entity, Id, ManyToOne, JoinColumn, Column } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_member",'tunnel')
export class Member extends BaseEntity{
	@Id()
	@ManyToOne({entity:Member,lazyFetch:true})
	@JoinColumn({name:'user_id',refName:'user_id'})
	user:User;

	@ManyToOne({entity:Member,lazyFetch:true})
	@JoinColumn({name:'func_company_id',refName:'func_company_id'})
	funcCompany:FuncCompany;

	@ManyToOne({entity:Member,lazyFetch:true})
	@JoinColumn({name:'agent_id',refName:'agent_id'})
	agent:Agent;

	@Column({
		name:'user_name',
		type:'string',
		nullable:true
	})
	userName:string;

	@Column({
		name:'password',
		type:'string',
		nullable:true
	})
	password:string;

	@Column({
		name:'head_img',
		type:'string',
		nullable:true
	})
	headImg:string;

	@Column({
		name:'nick_name',
		type:'string',
		nullable:true
	})
	nickName:string;

	@Column({
		name:'birthday',
		type:'string',
		nullable:true
	})
	birthday:string;

	@Column({
		name:'sex',
		type:'int',
		nullable:true
	})
	sex:number;

	@Column({
		name:'email',
		type:'string',
		nullable:true
	})
	email:string;

	@Column({
		name:'openid',
		type:'string',
		nullable:true
	})
	openid:string;

	@Column({
		name:'real_name',
		type:'string',
		nullable:true
	})
	realName:string;

	@Column({
		name:'id_no',
		type:'string',
		nullable:true
	})
	idNo:string;

	@Column({
		name:'idno_imgs',
		type:'string',
		nullable:true
	})
	idnoImgs:string;

	@Column({
		name:'regist_time',
		type:'int',
		nullable:true
	})
	registTime:number;

	@Column({
		name:'mobile',
		type:'string',
		nullable:true
	})
	mobile:string;

	@Column({
		name:'tel',
		type:'string',
		nullable:true
	})
	tel:string;

	@Column({
		name:'enabled',
		type:'int',
		nullable:true
	})
	enabled:number;

}